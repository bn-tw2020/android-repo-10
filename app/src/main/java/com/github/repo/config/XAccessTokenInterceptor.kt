package com.github.repo.config

import android.util.Log
import com.github.repo.data.datasource.TokenSharedPreference
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class XAccessTokenInterceptor(
    private val tokenSharedPreference: TokenSharedPreference
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()

        val jwtToken = "token " + tokenSharedPreference.getToken()
        builder.addHeader(GITHUB_ACCESS_TOKEN, jwtToken)
        builder.addHeader(GITHUB_ACCEPT, "application/vnd.github+json")

        return chain.proceed(builder.build())
    }

    companion object {
        const val GITHUB_ACCESS_TOKEN = "Authorization"
        const val GITHUB_ACCEPT = "Accept"
    }
}