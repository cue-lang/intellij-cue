rootProject.name = "intellij-cue"

pluginManagement {
    val platformVersion = (extra["platformVersion"] as String).toInt()
    plugins {
        kotlin("jvm") version when {
            platformVersion >= 262 -> "2.4.20"
            platformVersion >= 261 -> "2.3.10"
            platformVersion >= 252 -> "2.2.0"
            platformVersion == 251 -> "2.1.10"
            else -> "2.0.21"
        }
    }
}

run {
    val platform = platformProperties()

    gradle.lifecycle.beforeProject {
        platform.forEach { (key, value) -> extensions.extraProperties[key] = value }
    }
}

/**
 * Reads `gradle-$platformVersion.properties`, which describes the IDE this build targets.
 */
fun platformProperties(): Map<String, String> {
    val platformVersion = providers.gradleProperty("platformVersion").get()
    val propertiesFile = settingsDir.resolve("gradle-$platformVersion.properties")
    require(propertiesFile.isFile) { "Unsupported platformVersion '$platformVersion'." }

    return propertiesFile.inputStream().use { stream ->
        java.util.Properties()
            .apply { load(stream) }
            .entries
            .associate { (key, value) -> key.toString() to value.toString() }
    }
}
