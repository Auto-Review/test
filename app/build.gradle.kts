import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.25"
}

android {
    namespace = "com.dd2d.ori_android"
    compileSdk = 34

    val properties = Properties()
    properties.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.dd2d.ori_android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        val kakaoSignInSdkKey = properties.getProperty("kakao-sign-in-sdk-key")
        buildConfigField("String", "KAKAO_SIGN_IN_SDK_KEY", "\"${kakaoSignInSdkKey}\"")
        manifestPlaceholders["KAKAO_SIGN_IN_SDK_KEY"] = kakaoSignInSdkKey

        val googleSignInSdkKey = properties.getProperty("google-sign-in-sdk-key")
        buildConfigField("String", "GOOGLE_SIGN_IN_SDK_KEY", "\"${googleSignInSdkKey}\"")

        val baseUrl = properties.getProperty("base-url")
        buildConfigField("String", "BASE_URL", "\"${baseUrl}\"")
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // dagger-hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // hilt-viewModel
    implementation(libs.androidx.hilt.navigation.compose)

    // navigation
    implementation(libs.androidx.navigation.compose)

    // kotlinx-serialization
    implementation(libs.kotlinx.serialization.json)

    // compose-lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)

    // splash
    implementation(libs.androidx.core.splashscreen)

    // navigation-suite
    implementation(libs.androidx.material3.adaptive.navigation.suite)

    // adaptive-layout
    implementation(libs.androidx.adaptive)
    implementation(libs.androidx.adaptive.layout)
    implementation(libs.androidx.adaptive.navigation)

    // ktor
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // kakao sign in
    implementation(libs.kakao.v2.user) // 카카오 로그인 API 모듈

    // google sign in
    implementation(platform(libs.firebase.bom))
    implementation(libs.google.firebase.auth)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}
