plugins {
	`kotlin-dsl`
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.openapitools:openapi-generator-gradle-plugin:7.8.0")
	testImplementation(kotlin("test"))
}

tasks {
	test {
		useJUnitPlatform()
	}
}

