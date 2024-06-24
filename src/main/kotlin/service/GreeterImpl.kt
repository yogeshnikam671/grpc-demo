package service

import com.example.grpc.GreeterGrpcKt
import com.example.grpc.HelloReply
import com.example.grpc.HelloRequest

class GreeterImpl : GreeterGrpcKt.GreeterCoroutineImplBase() {

    override suspend fun sayHello(request: HelloRequest): HelloReply {
        println("Request --> $request")
        return HelloReply.newBuilder()
            .setMessage("Hello ${request.name}!")
            .build()
    }
}