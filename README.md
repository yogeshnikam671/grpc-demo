# GRPC with kotlin+gradle

## How to run locally?
1. Run `./gradlew clean build` [This generates the required grpc classes out of the hello.proto file]

#### Testing only server 
1. Go to `GrpcServer.kt` and run the `main` method from your IntelliJ IDE.
2. You can see the server started on port `50051` text in the application run logs.
3. Go to file `resources -> requests.http`.
4. This file contains the GRPC request which you can directly run from within the IntelliJ IDE.

#### Testing client along with server
1. Start the server as explained in the above section.
2. Go to `GreeterClient.kt` and run the `main` method.
3. This hits the server method and logs the response in the client run logs.
