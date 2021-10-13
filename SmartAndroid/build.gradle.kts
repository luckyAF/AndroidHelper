plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("com.luckyaf.plugin") apply false
    id("maven-publish")
}
group="com.github.luckyaf.androidhelper"

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
    api(Google.material)
    api(AndroidX.Lifecycle.runtime)
    api(AndroidX.Lifecycle.commonJava8)
    api(AndroidX.Lifecycle.viewModel)
    api(AndroidX.Lifecycle.viewModelKtx)
    api(AndroidX.Lifecycle.livedata)
    api(AndroidX.Lifecycle.liveDataKtx)
    api(AndroidX.recyclerView)
    api(AndroidX.Navigation.runtime)
    api(AndroidX.Navigation.ui)
    api(AndroidX.Navigation.fragment)
    api(AndroidX.Navigation.fragmentKtx)
    api(ThirdPart.kunminx.unpeekLivedata)

}