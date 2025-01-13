plugins {
    id("org.jetbrains.kotlin.multiplatform") version "2.0.21"
}

kotlin {
    mingwX64 {
        binaries {
            sharedLib {
                baseName = "DontStarve.Kotlin"
            }
        }
    }
}