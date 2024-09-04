plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = "com.lumston.finvivirchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lumston.finvivirchallenge"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
}

dependencies {
    // Core dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    /**
    * Dagger hilt
    *  */
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    /**
     * Room
     *  */
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    /**
    * Retrofit
    *  */
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    /**
    * Coroutines
    *  */
    implementation(libs.kotlinx.coroutines.android)

    /**
    * Lifecycle components
    *  */
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    /**
     * Maps for android
     *  */
    implementation(libs.play.services.maps)
    implementation(libs.places)

    /**
    * Testing ------------------------------------------------------------------
    * */
    testImplementation(libs.junit)
    // Coroutines testing
    testImplementation(libs.org.jetbrains.kotlinx.kotlinx.coroutines.test)
    // Lifecycle components tests
    testImplementation(libs.androidx.core.testing)
    // MockK
    testImplementation(libs.mockk)
    // Instrumented
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}