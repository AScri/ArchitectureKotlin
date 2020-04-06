package com.ascri.test.utils.di.modules

import com.ascri.test.BuildConfig
import com.ascri.test.data.dataSources.remote.RestAPI
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideJackson(): ObjectMapper {
        return ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_DEFAULT)
            .setDefaultSetterInfo(JsonSetter.Value.forValueNulls(Nulls.SKIP))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(KotlinModule(nullisSameAsDefault = true))
    }

    @Singleton
    @Provides
    fun provideServiceRetrofit(jackson: ObjectMapper, okHttpClient: OkHttpClient): RestAPI {
        return Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(jackson))
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .build().create(RestAPI::class.java)
    }
}