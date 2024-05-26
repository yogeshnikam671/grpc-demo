import com.google.protobuf.gradle.id

plugins {
    kotlin("jvm") version "1.9.23"
    id("com.google.protobuf") version "0.9.2"
}

group = "in.specmatic"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.grpc:grpc-kotlin-stub:1.3.0")
    implementation("io.grpc:grpc-protobuf:1.57.0")
    implementation("io.grpc:grpc-netty-shaded:1.57.0")
    implementation("com.google.protobuf:protobuf-kotlin:3.23.4")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // Optional: gRPC testing and other utilities
    testImplementation("io.grpc:grpc-testing:1.57.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.23.4"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.57.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("src/main/proto")
        }
        java {
            srcDirs("build/generated/source/proto/main/grpc", "build/generated/source/proto/main/grpckt")
        }
    }
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}