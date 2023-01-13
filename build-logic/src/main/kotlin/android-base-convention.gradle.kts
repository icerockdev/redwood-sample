import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    compileSdkVersion(32)

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
