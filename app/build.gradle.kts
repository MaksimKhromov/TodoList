plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.todolist"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.todolist"
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
}

dependencies {
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version") //room для создания БД
    annotationProcessor("androidx.room:room-compiler:$room_version") //room для создания БД
    implementation("androidx.room:room-rxjava3:$room_version") //поддержкой room rxJava3

    implementation("io.reactivex.rxjava3:rxandroid:3.0.2") // rxJava для использования многопоточности
    implementation("io.reactivex.rxjava3:rxjava:3.1.5") // rxJava для использования многопоточности

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}