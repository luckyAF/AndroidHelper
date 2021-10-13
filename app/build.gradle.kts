plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("com.luckyaf.plugin") apply false
}

android {
    compileSdk = BuildConfig.compileSdkVersion
    buildToolsVersion = BuildConfig.buildToolsVersion

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdk = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        versionCode =BuildConfig.versionCode
        versionName =BuildConfig.versionName
        testInstrumentationRunner =BuildConfig.testInstrumentationRunner
        multiDexEnabled = true
    }

    buildFeatures {
        viewBinding = true
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
    implementation(project(mapOf("path" to ":SmartAndroid")))
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}