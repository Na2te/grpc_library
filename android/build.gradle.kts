plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.wire)
    id("maven-publish")
}

group = "com.na2te.grpc.library"
version = "0.0.0"

wire {
    kotlin {
        rpcRole = "client"
    }
    sourcePath {
        srcDir("../src/main/proto")
    }
}

repositories {
    mavenCentral()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"]) // java 대신 'kotlin' 컴포넌트 배포
        }
    }
    repositories {
        maven {
            // GitLab 주소 및 인증
            url = uri("https://maven.pkg.github.com/Na2te/grpc_library")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}


dependencies {
    api(libs.kotlin.coroutine.android)
    api(libs.wire.grpc.client)
}
