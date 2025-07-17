import org.jetbrains.changelog.markdownToHTML

plugins { // Java support
    id("java")

    // gradle-intellij-plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "1.17.4"

    // gradle-changelog-plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.changelog") version "1.3.1"

    // gradle-grammarkit-plugin - read more: https://github.com/JetBrains/gradle-grammar-kit-plugin
    id("org.jetbrains.grammarkit") version "2021.2.1"
}

// Import variables from gradle.properties file
val pluginGroup: String by project // `pluginName_` variable ends with `_` because of the collision with Kotlin magic getter in the `intellij` closure. // Read more about the issue: https://github.com/JetBrains/intellij-platform-plugin-template/issues/29
val pluginName_: String by project
val pluginVersion: String by project
val pluginSinceBuild: String by project
val pluginUntilBuild: String by project
val pluginVerifierIdeVersions: String by project

val platformType: String by project
val platformVersion: String by project
val platformPlugins: String by project
val platformDownloadSources: String by project

group = pluginGroup
version = pluginVersion

// Configure project's dependencies
repositories {
    mavenCentral()
}

// setup additional source folders
sourceSets.main {
    java.srcDir("src/main/java-gen")
}

// Configure gradle-intellij-plugin plugin.
// Read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    pluginName.set(pluginName_)
    version.set(platformVersion)
    type.set(platformType)
    downloadSources.set(platformDownloadSources.toBoolean())
    updateSinceUntilBuild.set(true)

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(platformPlugins.split(',').map(String::trim).filter(String::isNotEmpty).toList())
}

// Configure gradle-changelog-plugin plugin.
// Read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(pluginVersion)
    groups.set(listOf("Added", "Changed", "Fixed"))
}

tasks { // disable building searchable options to speed up build, we currently don't settings UI
    buildSearchableOptions {
        enabled = false
    }

    withType<JavaCompile> {
        val jdkVersion = when {
            platformVersion.startsWith("2022.") -> "11"
            else -> "17"
        }
        sourceCompatibility = jdkVersion
        targetCompatibility = jdkVersion
    }

    withType<Test> {
        systemProperties["idea.tests.overwrite.data"] = project.property("overrideTestData") == "true"
    }

    patchPluginXml {
        version.set(pluginVersion)
        sinceBuild.set(pluginSinceBuild)
        untilBuild.set(pluginUntilBuild)

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(provider {
            file("${project.rootDir}/plugin-description.md").readText().run { markdownToHTML(this) }
        })

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            changelog.getLatest().toHTML()
        })
    }

    runPluginVerifier {
        ideVersions.set(provider {
            pluginVerifierIdeVersions.split(',')
        })
    }

    downloadZipSigner {
        enabled = false
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))

        // pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        channels.set(pluginVersion.split('-').getOrElse(1) { "default" }.split('.').take(1))
    }

    generateLexer {
        source.set("src/grammar/cue.flex")
        targetDir.set("src/main/java-gen/dev/monogon/cue/lang/lexer")
        targetClass.set("_CueLexerGen")
        purgeOldFiles.set(true)
    }
}