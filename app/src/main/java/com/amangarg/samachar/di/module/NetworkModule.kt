package com.amangarg.samachar.di.module

import com.amangarg.samachar.BuildConfig.API_KEY
import com.amangarg.samachar.BuildConfig.BASE_URL
import com.amangarg.samachar.data.remote.interceptor.ApiKeyInterceptor
import com.amangarg.samachar.data.remote.service.NetworkService
import com.amangarg.samachar.di.qualifier.ApiKey
import com.amangarg.samachar.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @ApiKey
    @Provides
    fun provideApiKey(): String = API_KEY

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = BASE_URL



    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideApiKeyInterceptor(@ApiKey apiKey: String): ApiKeyInterceptor =
        ApiKeyInterceptor(apiKey)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsApi(retrofit: Retrofit): NetworkService =
        retrofit.create(NetworkService::class.java)
}

