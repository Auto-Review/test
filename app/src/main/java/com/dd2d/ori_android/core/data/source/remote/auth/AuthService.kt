package com.dd2d.ori_android.core.data.source.remote.auth

import android.util.Log
import com.dd2d.ori_android.core.data.source.remote._common.DataResponseDTO
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.isSuccess
import javax.inject.Inject

class AuthService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun oAuth2(token: String): DataResponseDTO<String> {
        val response = client.post("/v1/api/auth/token") {
            setBody(token)
        }

        Log.i("LOG_CHECK", "AuthService :: getAccessToken() -> code : ${response.status.value}   ")


        return if(response.status.isSuccess().also { Log.d("LOG_CHECK", "AuthService :: getAccessToken() -> $it") }) {
            response.body<DataResponseDTO<String>>()
        }
        else {
            throw IllegalArgumentException("asdasdsa")
        }
    }
}