plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("com.luckyaf.plugin") apply false
}

android {
    compileSdkVersion(BuildConfig.compileSdkVersion)
    buildToolsVersion(BuildConfig.buildToolsVersion)

    defaultConfig {
        applicationId = BuildConfig.applicationId
        minSdkVersion = BuildConfig.minSdkVersion
        targetSdkVersion = BuildConfig.targetSdkVersion
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
        viewBinding true
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
    implementation(BuildConfig.kt_stdlib)
    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.vectordrawable)
    implementation(AndroidX.Lifecycle.liveDataKtx)
    implementation(AndroidX.Lifecycle.viewModelKtx)
    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.ui)
    implementation(Google.material)
    implementation(Google.gson)
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)
}