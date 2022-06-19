package com.mehroz.kmmapp.datasource.remote

import com.mehroz.ktorclientapp.data.remote.dto.PostRequest
import com.mehroz.ktorclientapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*


interface ApiService {

    suspend fun getPosts(): List<PostResponse>

    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient {
                    install(Logging) {
                        level = LogLevel.BODY
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer()
                    }
                }
            )
        }
    }
}