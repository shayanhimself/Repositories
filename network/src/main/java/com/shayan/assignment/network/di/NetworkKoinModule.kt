package com.shayan.assignment.network.di

import com.google.gson.GsonBuilder
import com.shayan.assignment.network.ApiConstants.BASE_URL
import com.shayan.assignment.network.ApiConstants.TIMEOUT_SECONDS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkKoinModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply { setLevel(Level.HEADERS) })
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory((GsonConverterFactory.create(GsonBuilder().create())))
            .client(get<OkHttpClient>())
            .build()
    }

    single<com.shayan.assignment.network.api.GithubService> {
        get<Retrofit>().create(com.shayan.assignment.network.api.GithubService::class.java)
    }

    factory {
        com.shayan.assignment.network.api.RemoteDataSource(githubService = get())
    }
}
