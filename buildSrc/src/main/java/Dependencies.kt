object Version {
    const val CORE = "1.8.0"
    const val APPCOMPAT = "1.4.2"
    const val MATERIAL = "1.6.1"
    const val CONSTRAINT_LAYOUT = "2.1.4"
    const val JUNIT = "4.13.2"
    const val JUNIT_TEST = "1.1.3"
    const val ESPRESSO = "3.4.0"
}

object Lib {
    object AndroidX {
        const val APPCOMPAT = "androidx.appcompat:appcompat:${Version.APPCOMPAT}"
        const val CORE = "androidx.core:core-ktx:${Version.CORE}"
        const val CONSTRAINT_LAYOUT =
            "androidx.constraintlayout:constraintlayout:${Version.CONSTRAINT_LAYOUT}"
    }

    object Material {
        const val MATERIAL = "com.google.android.material:material:${Version.MATERIAL}"
    }

    object Test {
        const val JUNIT = "junit:junit:${Version.JUNIT}"
        const val JUNIT_TEST = "androidx.test.ext:junit:${Version.JUNIT_TEST}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${Version.ESPRESSO}"
    }
}