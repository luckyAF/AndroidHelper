plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
    id("com.luckyaf.plugin") apply false
    id("maven-publish")
}

android{
    compileSdk = BuildConfig.compileSdkVersion
    defaultConfig {
        minSdk  = BuildConfig.minSdkVersion
        targetSdk = BuildConfig.targetSdkVersion
        testInstrumentationRunner =  BuildConfig.testInstrumentationRunner
    }
    buildFeatures {
        dataBinding =true
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.androidJunit)
    androidTestImplementation(Testing.espresso)

    api(AndroidX.appcompat)
    api(AndroidX.Lifecycle.runtime)
    api(AndroidX.Lifecycle.commonJava8)
    api(AndroidX.Lifecycle.viewModel)
    api(AndroidX.Lifecycle.livedata)
    api(AndroidX.recyclerView)
    api(AndroidX.Navigation.runtime)
    api(AndroidX.Navigation.fragment)
    api(ThirdPart.kunminx.unpeekLivedata)
//    api(ThirdPart.kunminx.smoothNavigation)
    api(ThirdPart.kunminx.strictDatabinding)


}