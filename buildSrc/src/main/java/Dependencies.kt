object Version {
    const val CORE = "1.8.0"
    const val APPCOMPAT = "1.4.2"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val JUNIT = "4.13.2"
    const val JUNIT_TEST = "1.1.3"
    const val ESPRESSO = "3.4.0"
    const val KOIN = "3.0.1"
    const val COROUTINE = "1.6.1"
    const val GLIDE = "4.13.0"
    const val RETROFIT2 = "2.9.0"
    const val OKHTTP3 = "4.9.3"
    const val MOSHI = "1.9.3"
    const val BROWSER = "1.4.0"
    const val VIEWMODEL = "2.2.0"
}

object Lib {
    object AndroidX {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
        const val CORE = "androidx.core:core-ktx:${Version.CORE}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
        const val BROWSER = "androidx.browser:browser:${Version.BROWSER}"
        const val VIEWMDOEL_KTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.VIEWMODEL}"
    }

    object Material {
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Version.JUNIT}"
        const val JUNIT_TEST = "androidx.test.ext:junit:${Version.JUNIT_TEST}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    }

    object KOIN {
        const val ANDROID = "io.insert-koin:koin-android:${Version.KOIN}"
        const val EXT = "io.insert-koin:koin-android-ext:${Version.KOIN}"
        const val TEST = "io.insert-koin:koin-test:${Version.KOIN}"
    }

    object RETROFIT {
        const val RETROFIT2 = "com.squareup.retrofit2:retrofit:${Version.RETROFIT2}"
        const val RETROFIT2_CONVERTER_SCALARS =
            "com.squareup.retrofit2:converter-scalars:${Version.RETROFIT2}"
        const val RETROFIT2_CONVERTER_MOSHI =
            "com.squareup.retrofit2:converter-moshi:${Version.RETROFIT2}"
    }

    object OkHttp3 {
        const val OKHTTP3 = "com.squareup.okhttp3:okhttp:${Version.OKHTTP3}"
        const val OKHTTP3_LOGGING_INTERCEPTER =
            "com.squareup.okhttp3:logging-interceptor:${Version.OKHTTP3}"
    }

    object Moshi {
        const val MOSHI = "com.squareup.moshi:moshi-kotlin:${Version.MOSHI}"
    }

    object GLIDE {
        const val GLIDE = "com.github.bumptech.glide:glide:${Version.GLIDE}"
        const val COMPILER = "com.github.bumptech.glide:compiler:${Version.GLIDE}"
    }

    object COROUTINE {
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.COROUTINE}"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.COROUTINE}"
        const val TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.COROUTINE}"
    }
}