package com.jino.version.plugin

/**
 * 各依赖的版本
 */

object Versions {
    val appcompat = "1.2.0"
    val coreKtx = "1.3.0"
    val lifeCycle_Common = "2.3.1"
    val constraintlayout = "2.0.4"
    val material = "1.4.0"
    val paging = "3.1.1"
    val timber = "4.7.1"
    val kotlin = "1.6.21"
    val koin = "2.1.5"
    val work = "2.2.0"
    val room = "2.3.0-alpha01"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val fragment = "1.2.1"
    val anko = "0.10.8"
    val activity = "1.2.1"
    val swiperefreshlayout = "1.1.0"
    val hilt = "2.42"
    val hilt_viewmodels = "1.0.0"

    val retrofit = "2.9.0"
    val glide = "4.12.0"

    val junit = "4.13.2"
    val junitExt = "1.1.3"
    val espressoCore = "3.4.0"

}

object AndroidX {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    val pagingRuntime = "androidx.paging:paging-runtime:${Versions.paging}"
    val pagingTest = "androidx.paging:paging-common:${Versions.paging}"

    val workRuntime = "androidx.work:work-runtime:${Versions.work}"
    val workTesting = "androidx.work:work-testing:${Versions.work}"

    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val material = "com.google.android.material:material:${Versions.material}"
    val swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swiperefreshlayout}"

    val activityKtx = "androidx.activity:activity-ktx:${Versions.activity}"
    val lifeCycle_Common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifeCycle_Common}"
}

object Room {
    val runtime = "androidx.room:room-runtime:${Versions.room}"
    val compiler = "androidx.room:room-compiler:${Versions.room}"
    val ktx = "androidx.room:room-ktx:${Versions.room}"
    val rxjava2 = "androidx.room:room-rxjava2:${Versions.room}"
    val testing = "androidx.room:room-testing:${Versions.room}"
}

object Hilt{
    val hilt_Android = "com.google.dagger:hilt-android:${Versions.hilt}"
    val hilt_lifecycle_viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodels}"
    val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    val hilt_testing = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    val hilt_android_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
}

object Fragment {
    val runtime = "androidx.fragment:fragment:${Versions.fragment}"
    val runtimeKtx = "androidx.fragment:fragment-ktx:${Versions.fragment}"
    val testing = "androidx.fragment:fragment-testing:${Versions.fragment}"
}

object Kt {
    val stdlibJdk7 = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val stdlibJdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val test = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"
    val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Koin {
    val core = "org.koin:koin-core:${Versions.koin}"
    val androidCore = "org.koin:koin-android:${Versions.koin}"
    val viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val androidScope = "org.koin:koin-android-scope:$${Versions.koin}"
}

object Anko {
    val common = "org.jetbrains.anko:anko-common:${Versions.anko}"
    val sqlite = "org.jetbrains.anko:anko-sqlite:${Versions.anko}"
    val coroutines = "org.jetbrains.anko:anko-coroutines:${Versions.anko}"
    val design = "org.jetbrains.anko:anko-design:${Versions.anko}" // For SnackBars
}

object Retrofit {
    val runtime = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
}

object Glide{
    val glide           = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_compiler  = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object Depend {

    val junit = "junit:junit:${Versions.junit}"
    val androidTestJunit = "androidx.test.ext:junit:${Versions.junitExt}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

