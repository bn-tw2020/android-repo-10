buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("com.google.gms:google-services:${Version.GOOGLE_SERVICE}")
        classpath("com.google.firebase:firebase-crashlytics-gradle:${Version.CRASHLYTICS}")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("7.2.1") apply (false)
    id("com.android.library") version ("7.2.1") apply (false)
    id("org.jetbrains.kotlin.android") version ("1.6.10") apply (false)
}

tasks.register("clean, Delete::class") {
    delete(rootProject.buildDir)
}