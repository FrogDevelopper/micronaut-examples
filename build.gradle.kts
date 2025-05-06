import com.google.protobuf.gradle.id;

plugins {
    id("io.micronaut.minimal.application") version "4.4.4"
    id("com.google.protobuf") version "0.9.5"
}

version = "0.1"
group = "com.frogdevelopment"

repositories {
    mavenCentral()
}

dependencies {
    implementation(mn.grpc.protobuf)
    implementation(mn.grpc.stub)
    implementation(mn.protobuf.java)

    implementation(mn.micronaut.pulsar)
    implementation(mn.micronaut.serde.jackson)
    implementation(mn.micronaut.protobuff.support)

    runtimeOnly(mn.logback.classic)
    runtimeOnly(mn.snakeyaml)

    testImplementation(mn.mockito.core)
    testImplementation(mn.assertj.core)
}

application {
    mainClass = "com.frogdevelopment.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.frogdevelopment.*")
    }
}

sourceSets {
    main {
        java {
            srcDirs(
                    "build/generated/source/proto/main/grpc",
                    "build/generated/source/proto/main/java"
            )
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${mn.versions.protobuf.get()}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${mn.versions.grpc.asProvider().get()}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").configureEach {
            plugins {
                id("grpc")
            }
        }
    }
}

tasks.getByName("classes").dependsOn("generateProto")

