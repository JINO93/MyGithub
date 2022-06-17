package com.jino.mygithub.common

import com.jino.mygithub.BuildConfig
import com.jino.mygithub.GITHUB_API_BASE_URL
import com.jino.mygithub.util.LogUtils
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitManager @Inject constructor(){

    companion object{
        const val TimeoutConnect = 30
        const val TimeoutRead = 30
    }

    private var retrofit: Retrofit

    init{
        val logger = HttpLoggingInterceptor{
            LogUtils.d("HttpLog",it)
        }
        logger.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        val client = OkHttpClient.Builder().apply {
//            addInterceptor(headerInterceptor)
            addInterceptor(logger)
            connectTimeout(TimeoutConnect.toLong(), TimeUnit.SECONDS)
            readTimeout(TimeoutRead.toLong(), TimeUnit.SECONDS)
        }.build()
        retrofit = Retrofit.Builder()
            .baseUrl(GITHUB_API_BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }
}