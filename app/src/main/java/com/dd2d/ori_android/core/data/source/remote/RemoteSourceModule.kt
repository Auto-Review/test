package com.dd2d.ori_android.core.data.source.remote

import com.dd2d.ori_android.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteSourceModule {
    @Provides
    @Singleton
    fun provideClient(): HttpClient = HttpClient(CIO) {
        defaultRequest {
            url(BuildConfig.BASE_URL)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                    prettyPrint = true
                }
            )
        }
    }


}