plugins {
	kotlin("jvm") version "2.0.20"
	kotlin("plugin.spring") version "2.0.20"
	id("org.springframework.boot") version "3.3.3"
	id("io.spring.dependency-management") version "1.1.6"
	id("openapi-module-generate")
}

group = "de.jball.kotlin"
version = "0.1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
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
		gradleVersion = "8.10"
	}
	compileKotlin {
		dependsOn("openApiGenerate")
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
	inputSpec = "$rootDir/api/artifactsmmo.yaml"
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
