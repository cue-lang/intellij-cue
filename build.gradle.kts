import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType

plugins {
    idea
    id("java")
    id("org.jetbrains.intellij.platform") version "2.7.1" // https://github.com/JetBrains/intellij-platform-gradle-plugin/
    id("org.jetbrains.changelog") version "2.3.0" // https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.grammarkit") version "2022.3.2.2" // https://plugins.gradle.org/plugin/org.jetbrains.grammarkit
}

val pluginVersion: String by project
val platformVersion: String by project
val pluginSinceBuild: String by project

group = "dev.monogon.cuelang"
version = pluginVersion

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

sourceSets.main { // setup additional source folders
    java.srcDir("src/main/java-gen")
}

dependencies {
    intellijPlatform {
        pluginVerifier()

        intellijIdeaCommunity(platformVersion)
        testFramework(TestFrameworkType.Bundled)

        bundledPlugin("org.intellij.intelliLang")
    }

    // workaround for https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/1663
    testImplementation("org.opentest4j:opentest4j:1.3.0")
    testImplementation("junit:junit:4.13.2") // https://mvnrepository.com/artifact/junit/junit
}

intellijPlatform {
    pluginConfiguration {
        version = pluginVersion

        ideaVersion {
            sinceBuild = pluginSinceBuild
            untilBuild = provider { null } // JetBrains is recommending an unlimited untilBuild now
        }

        description = provider {
            file("plugin-description.md").readText().run(::markdownToHTML)
        }

        changeNotes = provider {
            changelog.renderItem(changelog.get(pluginVersion).withHeader(false).withEmptySections(false), Changelog.OutputType.HTML)
        }
    }

    pluginVerification {
        ides { // earliest supported major version
            select {
                sinceBuild = "242"
                untilBuild = "242.*"
                types.set(listOf(IntelliJPlatformType.IntellijIdeaCommunity))
            }

            // latest supported major version
            select {
                sinceBuild = "252"
                untilBuild = "252.*"
                types.set(listOf(IntelliJPlatformType.IntellijIdeaCommunity))
            }
        }
    }

    publishing {
        token = provider {
            System.getenv("PUBLISH_TOKEN")
        }
    }
}

changelog {
    version.set(pluginVersion)
    path.set("${project.rootDir}/CHANGELOG.md")
    header.set(provider { "[$version]}" })
    itemPrefix.set("-")
    keepUnreleasedSection.set(true)
    unreleasedTerm.set("[Unreleased]")
}

idea {
    module {
        generatedSourceDirs.add(project.file("src/main/java-gen"))
        isDownloadSources = true
    }
}

tasks {
    generateLexer {
        sourceFile = project.file("src/grammar/cue.flex")
        targetOutputDir = project.file("src/main/java-gen/dev/monogon/cue/lang/lexer")
        purgeOldFiles = true
    }
}