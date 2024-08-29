import org.openapitools.generator.gradle.plugin.extensions.OpenApiGeneratorGenerateExtension

plugins {
	id("org.openapi.generator")
}

interface OpenApiModuleGeneratorPluginExtension {
	val configurations: ListProperty<OpenApiGeneratorGenerateExtension>
}

val extension = project.extensions.create<OpenApiModuleGeneratorPluginExtension>("openApiModuleGenerator")
extension.configurations.convention(mutableListOf<OpenApiGeneratorGenerateExtension>())
