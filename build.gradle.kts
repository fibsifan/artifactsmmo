plugins {
    kotlin("jvm") version "2.0.0"
}

group = "de.jball.kotlin"
version = "0.1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks {
    test {
        useJUnitPlatform()
    }
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
        gradleVersion = "8.5"
    }
}

kotlin {
    jvmToolchain(21)
}
