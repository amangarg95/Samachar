plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.amangarg.samachar"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.amangarg.samachar"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
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

        debug {
            buildConfigField("String", "API_KEY", "\"03699ab2c2c343b8b3e6b350aa7c6b2c\"")
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/v2/\"")
            buildConfigField("String", "DB_NAME", "\"samachar_db\"")
            isDebuggable = true
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Core Android libraries with Kotlin extensions
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose BOM - manages all Compose library versions
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    // MVVM Architecture Components
    // ViewModel for managing UI-related data in lifecycle-conscious way
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Dependency Injection with Hilt
    // Provides a standard way to incorporate Dagger DI into Android apps
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // Networking Stack
    // Retrofit for type-safe HTTP client
    implementation(libs.retrofit)
    // Gson converter for JSON serialization/deserialization
    implementation(libs.retrofit.gson)
    // OkHttp for efficient HTTP connections
    implementation(libs.okhttp)
    // Logging interceptor for debugging network requests
    implementation(libs.okhttp.logging.interceptor)

    // Kotlin Coroutines for asynchronous programming
    // Core coroutines library
    implementation(libs.kotlinx.coroutines.core)
    // Android-specific coroutines support
    implementation(libs.kotlinx.coroutines.android)

    // Room Database for local data persistence
    // Runtime library for Room
    implementation(libs.androidx.room.runtime)
    // Room annotation processor
    kapt(libs.androidx.room.compiler)
    // Room Kotlin extensions with coroutine support
    implementation(libs.androidx.room.ktx)

    // Unit Testing Dependencies
    // JUnit for basic unit testing
    testImplementation(libs.junit)
    // Mockito for creating mock objects in tests
    testImplementation(libs.mockito.core)
    // Coroutines testing support
    testImplementation(libs.kotlinx.coroutines.test)

    // AndroidX Testing Libraries
    // JUnit extensions for Android
    androidTestImplementation(libs.androidx.junit)
    // Espresso for UI testing
    androidTestImplementation(libs.androidx.espresso.core)
    // Test core utilities
    androidTestImplementation(libs.androidx.test.core)
    // Test runner for instrumentation tests
    androidTestImplementation(libs.androidx.test.runner)
    // Test rules for common testing patterns
    androidTestImplementation(libs.androidx.test.rules)
    // Compose testing
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    // Mockito for Android instrumentation tests
    androidTestImplementation(libs.mockito.android)

    // Debug Dependencies (only included in debug builds)
    // Compose UI tooling for preview and debugging
    debugImplementation(libs.androidx.compose.ui.tooling)
    // Test manifest for debugging
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation(libs.coil.compose)

    implementation(libs.textflow.material3)
}