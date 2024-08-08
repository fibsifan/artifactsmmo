plugins {
	kotlin("jvm") version "2.0.0"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.openapi.generator") version "7.7.0"
}

group = "de.jball.kotlin"
version = "0.1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":api-client"))

	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation(kotlin("test"))
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
	withType<Test> {
		useJUnitPlatform()
	}
	wrapper {
		distributionType = Wrapper.DistributionType.ALL
		gradleVersion = "8.9"
	}

	compileKotlin {
		dependsOn(openApiGenerate)
	}
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

kotlin {
	jvmToolchain(21)
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

openApiGenerate {
	generatorName = "kotlin"
	inputSpec = "$rootDir/api-client/artifactsmmo.yaml"
	outputDir = "$rootDir/api-client"
	//apiPackage = "de.jball.kotlin.artifactsmmo.client.api"
	//modelPackage = "de.jball.kotlin.artifactsmmo.client.model"
	//invokerPackage = "de.jball.kotlin.artifactsmmo.client.invoker"
	packageName = "de.jball.kotlin.artifactsmmo.client"
	configOptions = mapOf(
		"modelDocs" to "false",
		"omitGradleWrapper"	to "true"
	)
}
