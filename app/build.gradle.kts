plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.broadcast_worker"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.broadcast_worker"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.gms:play-services-appindexing:9.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
//    val core_version = "1.13.1"
//
//    // Java language implementation
//    implementation("androidx.core:core:$core_version")
//    // Kotlin
//    implementation("androidx.core:core-ktx:$core_version")
//
//    // To use RoleManagerCompat
//    implementation("androidx.core:core-role:1.0.0")
//
//    // To use the Animator APIs
//    implementation("androidx.core:core-animation:1.0.0")
//    // To test the Animator APIs
//    androidTestImplementation("androidx.core:core-animation-testing:1.0.0")
//
//    // Optional - To enable APIs that query the performance characteristics of GMS devices.
//    implementation("androidx.core:core-performance:1.0.0")
//
//    // Optional - to use ShortcutManagerCompat to donate shortcuts to be used by Google
//    implementation("androidx.core:core-google-shortcuts:1.1.0")
//
//    // Optional - to support backwards compatibility of RemoteViews
//    implementation("androidx.core:core-remoteviews:1.1.0-beta02")
//
//    // Optional - APIs for SplashScreen, including compatibility helpers on devices prior Android 12
//    implementation("androidx.core:core-splashscreen:1.2.0-alpha01")

}