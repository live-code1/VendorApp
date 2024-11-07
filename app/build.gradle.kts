plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.vendorapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.vendorapp"
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

    hilt {
        enableAggregatingTask = true
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
        dataBinding = true
    }
}

dependencies {
    // Core Android Libraries
    implementation("androidx.core:core-ktx:1.13.1") // Kotlin extensions for Android core components
    implementation("androidx.appcompat:appcompat:1.7.0") // Support for backward-compatible Android features

// Material Design and UI Components
    implementation("com.google.android.material:material:1.12.0") // Material design components
    implementation("androidx.constraintlayout:constraintlayout:2.1.4") // Layout for complex UIs
    implementation("androidx.viewpager2:viewpager2:1.1.0") // ViewPager2 for swiping views
    implementation("me.relex:circleindicator:2.1.6") // Circular indicators for ViewPager2
    implementation("de.hdodenhof:circleimageview:3.1.0") // Circular ImageView
    implementation("com.facebook.shimmer:shimmer:0.5.0") // Shimmer effect for loading placeholders

// Navigation Components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7") // Navigation component for fragment management
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7") // Navigation UI helpers

// Lifecycle and ViewModel Libraries
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4") // ViewModel for UI data handling
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4") // LiveData for observable data holders
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4") // Lifecycle-aware components

// Dependency Injection with Hilt
    implementation("com.google.dagger:hilt-android:2.51") // Hilt for dependency injection
    kapt("com.google.dagger:hilt-android-compiler:2.51") // Hilt compiler for code generation

// Data Storage
    implementation("androidx.datastore:datastore-preferences:1.1.1") // DataStore for key-value storage
    implementation("androidx.datastore:datastore-core:1.1.1") // Core DataStore library
    implementation("io.github.pilgr:paperdb:2.7.2") // Simple NoSQL database for fast data storage

// Image Loading and Manipulation
    implementation("com.github.bumptech.glide:glide:4.14.2") // Glide for image loading and caching
    implementation("com.vanniktech:android-image-cropper:4.3.3") // Image cropping library
    implementation("id.zelory:compressor:3.0.1") // Image compression library

// Network and HTTP Clients
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Retrofit for HTTP requests
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.8") // Logging interceptor for network requests

// Permissions and Device Features
    implementation("com.karumi:dexter:6.2.3") // Runtime permissions handling
    implementation("com.hbb20:ccp:2.6.1") // Country code picker

// Paging Library
    implementation("androidx.paging:paging-runtime-ktx:3.3.2") // Paging library for large data sets

// Room Database with RxJava Support
    implementation("androidx.room:room-runtime:2.6.1") // Room database for local data persistence
    implementation("androidx.room:room-rxjava2:2.6.1") // Room RxJava support for reactive programming

// JWT and Cryptography
    implementation("com.nimbusds:nimbus-jose-jwt:9.12") // JSON Web Token (JWT) handling
    implementation("org.bitbucket.b_c:jose4j:0.9.5") // JOSE library for JWS, JWE, and JWK support

// RxJava for Asynchronous Operations
    implementation("io.reactivex.rxjava2:rxkotlin:2.4.0") // RxKotlin extensions for RxJava
    implementation("io.reactivex.rxjava2:rxandroid:2.1.0") // RxAndroid extensions for main thread scheduling

// Coroutine Support
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") // Coroutines for async programming

// Razorpay Payment Gateway
    implementation("com.razorpay:checkout:1.6.18") // Razorpay integration for payment handling

// QR Code Scanner
    implementation("com.journeyapps:zxing-android-embedded:4.3.0") // QR code scanning

// Legacy Support
    implementation("androidx.legacy:legacy-support-v4:1.0.0") // Backward compatibility support

// Dialog Library
    implementation("com.itlgl:iosdialog:1.0.1") // iOS style dialogs for Android

// Testing Libraries
    testImplementation("junit:junit:4.13.2") // JUnit for unit testing
    androidTestImplementation("androidx.test.ext:junit:1.1.5") // AndroidX JUnit for UI tests
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1") // Espresso for UI testing


}
