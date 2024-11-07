buildscript {
    val kotlin_version = "1.9.20"

    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://www.jitpack.io") }
    }

    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        classpath("com.android.tools.build:gradle:8.5.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

plugins {
    id("com.android.application") version "8.5.1" apply false
    id("com.android.library") version "8.5.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}
