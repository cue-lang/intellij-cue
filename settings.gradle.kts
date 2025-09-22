rootProject.name = "intellij-cue"

pluginManagement {
    val platformVersion = (extra["platformVersion"] as String).toInt()
    plugins {
        kotlin("jvm") version when {
            platformVersion >= 252 -> "2.2.0"
            platformVersion == 251 -> "2.1.10"
            else -> "2.0.21"
        }
    }
}