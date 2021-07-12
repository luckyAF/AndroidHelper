plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("com.luckyaf.plugin") apply false
}

android{
    compileSdkVersion(BuildConfig.compileSdkVersion)
    buildToolsVersion(BuildConfig.buildToolsVersion)

    defaultConfig{
        minSdkVersion(BuildConfig.minSdkVersion)
        targetSdkVersion(BuildConfig.targetSdkVersion)
        versionCode =BuildConfig.versionCode
        versionName =BuildConfig.versionName

        testInstrumentationRunner =BuildConfig.testInstrumentationRunner

        multiDexEnabled = true
    }

    buildTypes {
        getByName("release") {
            consumerProguardFiles("proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding =  true
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
    api(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    api(BuildConfig.kt_stdlib)
    api(AndroidX.coreKtx)
    api(AndroidX.appcompat)
    api(Google.material)
    api(Google.gson)
    api(AndroidX.constraintlayout)
    api(AndroidX.Lifecycle.livedata)
    api(AndroidX.Lifecycle.liveDataKtx)
    api(AndroidX.Lifecycle.runtime)
    api(AndroidX.Lifecycle.viewModel)
    api(AndroidX.Lifecycle.viewModelKtx)
    api(AndroidX.Lifecycle.viewModelSavedState)
    api(AndroidX.Lifecycle.commonJava8)
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}