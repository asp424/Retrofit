plugins { listPlugins.forEach { id(it) } }

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "com.lm.retrofit"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        with(JavaVersion.VERSION_1_8) {
            sourceCompatibility = this
            targetCompatibility = this
        }
    }

    kotlinOptions { jvmTarget = "1.8" }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = composeCompilerVersion }
    packagingOptions { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}
repositories { myRepository() }

dependencies { impl() }
