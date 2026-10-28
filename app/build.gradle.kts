plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.tiviport.app"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.tiviport.app"
        minSdk = 23
        targetSdk = 35
        versionCode = 21
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            // Читаем ключ из секретов GitHub (Base64)
            val keystoreBase64 = System.getenv("KEYSTORE_BASE64") ?: ""
            if (keystoreBase64.isNotEmpty()) {
                val keystoreFile = file("${rootDir}/release.keystore")
                keystoreFile.writeBytes(java.util.Base64.getDecoder().decode(keystoreBase64))
                storeFile = keystoreFile
                storePassword = System.getenv("KEYSTORE_PASSWORD")
                keyAlias = System.getenv("KEY_ALIAS")
                keyPassword = System.getenv("KEY_PASSWORD")
            } else {
                // Запасной вариант для локальной сборки
                val storePath = providers.environmentVariable("KEYSTORE_PATH").orNull
                if (storePath != null) {
                    storeFile = file(storePath)
                    storePassword = providers.environmentVariable("KEYSTORE_PASSWORD").orNull
                    keyAlias = providers.environmentVariable("KEY_ALIAS").orNull
                    keyPassword = providers.environmentVariable("KEY_PASSWORD").orNull
                }
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            // Используем наш постоянный ключ, если он есть
            signingConfig = if (System.getenv("KEYSTORE_BASE64") != null || providers.environmentVariable("KEYSTORE_PATH").orNull != null) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug")
            }
        }
        debug {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            // Применяем ту же подпись к debug-сборке, чтобы она ставилась поверх
            if (System.getenv("KEYSTORE_BASE64") != null) {
                signingConfig = signingConfigs.getByName("release")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.3")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.icons)
    debugImplementation(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.media3.exoplayer)
    implementation(libs.media3.exoplayer.hls)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.media3.exoplayer.rtsp)
    implementation(libs.media3.ui)
    implementation(libs.media3.session)
    implementation(libs.media3.datasource.okhttp)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.work.runtime)
    implementation(libs.androidx.datastore.preferences)
    // Storage Access Framework helpers - write recordings to a plugged-in USB / external
    // drive via a user-granted tree URI, with no storage permission.
    implementation(libs.androidx.documentfile)
    implementation(libs.coil.compose)
    implementation(libs.zxing.core)

    // SMB/CIFS client for recording to a NAS (Synology etc.) and playing those recordings
    // back in-app over the network. Pure-Java SMB2/3, no native bits.
    implementation("com.hierynomus:smbj:0.12.2")

    testImplementation(libs.junit)
    testImplementation(libs.truth)
    testImplementation(libs.robolectric)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.room.testing)
    testImplementation(libs.androidx.test.ext.junit)
}
