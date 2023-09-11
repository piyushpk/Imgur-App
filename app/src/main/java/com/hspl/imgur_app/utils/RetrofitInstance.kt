package com.hspl.imgur_app.utils

import android.util.Log
import com.hspl.imgur_app.BuildConfig
import com.hspl.imgur_app.`interface`.RetrofitInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofitInterface: RetrofitInterface by lazy {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("API Details", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = if (BuildConfig.RETROFIT_LOG_INTERCEPTOR) {
            OkHttpClient.Builder()
                .addInterceptor(getHeaderInterceptor())
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            OkHttpClient.Builder()
                .build()
        }

        Retrofit.Builder()
            .baseUrl(AppConstants.baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(RetrofitInterface::class.java)
    }

    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Authorization", "Client-ID c795b8886cc3233")
                .build()
            chain.proceed(request)
        }
    }
}