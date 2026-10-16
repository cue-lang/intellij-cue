import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    idea
    id("java")
    kotlin("jvm")

    id("org.jetbrains.intellij.platform") version "2.19.0" // https://github.com/JetBrains/intellij-platform-gradle-plugin/
    id("org.jetbrains.intellij.platform.grammarkit") version "2.19.0"
    id("org.jetbrains.changelog") version "2.4.0" // https://github.com/JetBrains/gradle-changelog-plugin
}

val platformVersion: Int = project.property("platformVersion")!!.toString().toInt() // load additional platform properties

val pluginVersion: String = project.findProperty("pluginVersion").toString()
val ideVersion: String = project.property("ideVersion").toString()
val pluginSinceBuild: String = project.property("pluginSinceBuild").toString()

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
        val javaVersion = when {
            platformVersion >= 262 -> 25
            else -> 21
        }
        languageVersion = JavaLanguageVersion.of(javaVersion)
    }
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

sourceSets.main { // setup additional source folders
    java.srcDir("src/main/java-gen")
}

dependencies {
    intellijPlatform {
        pluginVerifier()

        when {
            platformVersion >= 253 -> intellijIdea(ideVersion) // 2025.3 only has unified product builds
            else -> create(IntelliJPlatformType.IntellijIdeaUltimate, ideVersion)
        }
        testFramework(TestFrameworkType.Platform)
        testFramework(TestFrameworkType.Bundled)

        // 2024.3 extracted the built-in JSON support into a plugin, we need it for our tests
        bundledPlugin("com.intellij.modules.json")

        // 2025.3 extracted the IntelliLang plugin into a module
        when {
            platformVersion >= 253 -> bundledModule("intellij.platform.langInjection")
            else -> bundledPlugin("org.intellij.intelliLang")
        }

        // 2026.2 extracted the structure view into a module
        if (platformVersion >= 262) {
            bundledModule("intellij.platform.structureView")
        }
    }

    // workaround for https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/1663
    testImplementation("org.opentest4j:opentest4j:1.3.0")
    testImplementation("junit:junit:4.13.2") // https://mvnrepository.com/artifact/junit/junit
}

intellijPlatform {
    sandboxContainer.set(rootProject.layout.projectDirectory.dir(".intellijPlatform/sandbox"))

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
            val changelogItem = changelog.getOrNull(pluginVersion) ?: changelog.getUnreleased()
            changelog.renderItem(changelogItem.withHeader(false).withEmptySections(false), Changelog.OutputType.HTML)
        }
    }

    pluginVerification {
        ides { // earliest supported major version
            select {
                sinceBuild = "243"
                untilBuild = "243.*"
                types.set(listOf(IntelliJPlatformType.IntellijIdeaCommunity))
            }

            // latest supported major version
            select {
                sinceBuild = "262"
                untilBuild = "262.*"
                types.set(listOf(IntelliJPlatformType.IntellijIdea))
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
    version = pluginVersion
    path = rootDir.resolve("CHANGELOG.md").canonicalPath
    header = provider { pluginVersion }
    itemPrefix = "-"
    keepUnreleasedSection = true
    unreleasedTerm = "[Unreleased]"
    groups = listOf("Added", "Changed", "Fixed")
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
        targetRootOutputDir = project.file("src/main/java-gen")
        purgeOldFiles = false
    }
}