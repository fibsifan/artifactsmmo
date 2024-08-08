plugins {
	kotlin("jvm") version "2.0.0"
	id("org.openapi.generator") version "7.7.0"
}

group = "de.jball.kotlin"
version = "0.1.0-SNAPSHOT"

repositories {
	mavenCentral()
}

dependencies {
	implementation(project(":api-client"))
	testImplementation(kotlin("test"))
}

tasks {
	test {
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

kotlin {
	jvmToolchain(21)
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
