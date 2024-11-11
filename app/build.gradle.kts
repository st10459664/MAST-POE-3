plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize") // Enables Kotlin Parcelize functionality
}

android {
    namespace = "com.example.st10459664mastpart2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.st10459664mastpart2"
        minSdk = 28  // Updated minSdk to 28
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

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    // Core Android KTX libraries
    implementation("androidx.core:core-ktx:1.13.1")

    // AndroidX AppCompat for backward compatibility
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Material Design components
    implementation("com.google.android.material:material:1.12.0")

    // ConstraintLayout for flexible UI layouts
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Activity-related functionality
    implementation("androidx.activity:activity:1.9.3")

    // ViewModel & LiveData for lifecycle-conscious data management
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")

    // Unit testing framework
    testImplementation("junit:junit:4.13.2")

    // AndroidX testing libraries for UI tests
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}




