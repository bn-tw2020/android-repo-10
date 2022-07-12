plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "com.github.repo"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(Lib.AndroidX.CORE)
    implementation(Lib.AndroidX.APPCOMPAT)
    implementation(Lib.Material.MATERIAL)
    implementation(Lib.AndroidX.CONSTRAINT_LAYOUT)
    testImplementation(Lib.Test.JUNIT)
    androidTestImplementation(Lib.Test.JUNIT_TEST)
    androidTestImplementation(Lib.Test.ESPRESSO)
    implementation(Lib.KOIN.ANDROID)
    implementation(Lib.KOIN.EXT)
    testImplementation(Lib.KOIN.TEST)
    implementation(Lib.COROUTINE.CORE)
    implementation(Lib.COROUTINE.ANDROID)
    implementation(Lib.COROUTINE.TEST)
    implementation(Lib.GLIDE.GLIDE)
    kapt(Lib.GLIDE.COMPILER)
    implementation(Lib.RETROFIT.RETROFIT2)
    implementation(Lib.RETROFIT.RETROFIT2_CONVERTER_SCALARS)
    implementation(Lib.RETROFIT.RETROFIT2_CONVERTER_MOSHI)
    implementation(Lib.OkHttp3.OKHTTP3)
    implementation(Lib.OkHttp3.OKHTTP3_LOGGING_INTERCEPTER)
    implementation(Lib.Moshi.MOSHI)
    implementation(Lib.AndroidX.VIEWMDOEL_KTX)
    implementation(Lib.AndroidX.BROWSER)
}