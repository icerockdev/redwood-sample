plugins {
    id("base-convention")
    id("com.android.library")
    id("kotlin-android")
    id("android-base-convention")
}

android {
    sourceSets.all { java.srcDir("src/$name/kotlin") }
}
