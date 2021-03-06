/**
 * @author haizhuo
 * @description 编译配置信息
 */
object BuildConfig {
    const val compileSdkVersion = 30
    const val buildToolsVersion ="30.0.3"
    const val minSdkVersion = 19
    const val targetSdkVersion = 30

    const val applicationId ="com.luckyaf.androidhelper"
    const val testInstrumentationRunner="androidx.test.runner.AndroidJUnitRunner"

    var versionName = "1.0.1"
    var versionCode = 2

    var gradle_version = "4.1.1"
    var kotlin_version = "1.5.21"
    val kt_stdlib get()= "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"

    object Kotlin {
        var stdlib ="org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
        val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
        val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        val test = "org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version"
        val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}