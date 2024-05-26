package client

import com.example.grpc.GreeterGrpc
import com.example.grpc.HelloRequest
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

class GreeterClient(
    private val channel: ManagedChannel
) {
    // This stub object lets us make grpc calls as if we are calling the methods on the server
    private val blockingStub = GreeterGrpc.newBlockingStub(channel)

    fun greet(name: String) {
        val request = HelloRequest.newBuilder()
            .setName(name)
            .build()

        // this simple method call is in fact a GRPC call.
        val response = blockingStub.sayHello(request)

        println("Response: ${response.message}")
    }
}

fun main() {
    val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build()
    val client = GreeterClient(channel)

    try {
        client.greet("GRPC")
    } finally {
        channel.shutdownNow()
    }
}