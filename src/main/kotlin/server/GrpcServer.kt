package server

import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionService
import service.GreeterImpl

class GrpcServer {
    private val port = 50051
    private val server = ServerBuilder.forPort(port)
        .addService(GreeterImpl())
        .addService(ProtoReflectionService.newInstance())
        .build()

    fun start() {
        server.start()
        println("Server started. Listening on port: $port")

        Runtime.getRuntime().addShutdownHook(Thread {
            println("*** Shutting down gRPC server since JVM is shutting down")
            stop()
            println("*** Server shutdown")
        })
    }

    fun stop() = server.shutdown()

    fun blockUntilShutdown() = server.awaitTermination()
}

fun main() {
    val server = GrpcServer()
    server.start()
    server.blockUntilShutdown()
}