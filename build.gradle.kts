import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.TestFrameworkType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    idea
    id("java")
    kotlin("jvm")
    id("org.jetbrains.intellij.platform") version "2.13.1" // https://github.com/JetBrains/intellij-platform-gradle-plugin/
    id("org.jetbrains.changelog") version "2.4.0" // https://github.com/JetBrains/gradle-changelog-plugin
    id("org.jetbrains.grammarkit") version "2023.3.0.3" // https://plugins.gradle.org/plugin/org.jetbrains.grammarkit
}

val platformVersion: Int = project.property("platformVersion")!!.toString().toInt() // load additional platform properties
loadProperties(rootDir.resolve("gradle-${platformVersion}.properties").toString()).forEach {
    rootProject.extra.set(it.key.toString(), it.value)
}

val pluginVersion: String by project
val ideVersion: String by project
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

        // 2025.3 extracted the IntelliLang plugin into a module
        when {
            platformVersion >= 253 -> bundledModule("intellij.platform.langInjection")
            else -> bundledPlugin("org.intellij.intelliLang")
        }

        // 2024.3 extracted the built-in JSON support into a plugin, we need it for our tests
        if (platformVersion >= 243) {
            bundledPlugin("com.intellij.modules.json")
        }
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
                sinceBuild = "261"
                untilBuild = "261.*"
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
    // The tests read their data from src/test/data at run time. Declare it as
    // an input, so that changing the data runs the tests again rather than
    // reusing results from the build cache.
    test {
        inputs.dir("src/test/data").withPathSensitivity(PathSensitivity.RELATIVE)
    }

    generateLexer {
        sourceFile = project.file("src/grammar/cue.flex")
        targetOutputDir = project.file("src/main/java-gen/dev/monogon/cue/lang/lexer")
        purgeOldFiles = true
    }
    generateParser {
        sourceFile = project.file("src/grammar/cue.bnf")
        targetRootOutputDir = project.file("src/main/java-gen")
        pathToParser = "dev/monogon/cue/lang/parser/CueParser.java"
        pathToPsiRoot = "dev/monogon/cue/lang/psi"
        purgeOldFiles = true
        // The generator finds the methods of psiImplUtilClass in the compiled
        // classes; the committed generated sources make them compilable first.
        dependsOn("compileJava", "compileKotlin")
        doFirst {
            classpath = classpath + files(sourceSets.main.get().output, configurations.compileClasspath)
        }
    }

    // syncSyntaxCorpus refreshes the copy of the CUE syntax corpus that
    // CueSyntaxCorpusTest reads, the .cue files in cue/parser/testdata/syntax,
    // from a checkout of cue-lang/cue that is exactly at a tag, removes any
    // directory, and records the tag in SOURCE:
    //
    //     ./gradlew syncSyntaxCorpus -PcueCheckout=<path>
    register("syncSyntaxCorpus") {
        group = "verification"
        description = "Refreshes the CUE syntax corpus test data from a tagged cue-lang/cue checkout."
        doLast {
            val checkout = file(
                providers.gradleProperty("cueCheckout").orNull
                    ?: throw GradleException("set -PcueCheckout to a cue-lang/cue checkout at a tag")
            )
            val git = ProcessBuilder("git", "-C", checkout.path, "describe", "--exact-match", "--tags")
                .redirectErrorStream(true)
                .start()
            val tag = git.inputStream.bufferedReader().readText().trim()
            if (git.waitFor() != 0) {
                throw GradleException("$checkout is not a git checkout exactly at a tag")
            }
            val source = checkout.resolve("cue/parser/testdata/syntax")
            val target = file("src/test/data/lang/cue/syntax")
            val names = source.listFiles { f -> f.name.endsWith(".cue") }?.map { it.name }?.toSet().orEmpty()
            if (names.isEmpty()) {
                throw GradleException("no corpus files in $source")
            }
            target.listFiles { f -> f.name.endsWith(".cue") && f.name !in names }?.forEach {
                it.delete()
                target.resolve(it.name.removeSuffix(".cue") + ".txt").delete()
            }
            target.listFiles { f -> f.isDirectory }?.forEach { it.deleteRecursively() }
            names.forEach { source.resolve(it).copyTo(target.resolve(it), overwrite = true) }
            target.resolve("SOURCE").writeText("cue-lang/cue $tag\n")
            println("copied ${names.size} corpus files from cue-lang/cue $tag")
        }
    }
}