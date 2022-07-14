package com.github.repo.data.network

import com.github.repo.data.dto.GithubNotificationDto
import com.github.repo.data.dto.GithubTokenDto
import retrofit2.http.*

interface GitHubService {

    @FormUrlEncoded
    @POST("login/oauth/access_token")
    @Headers("Accept: application/json")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): GithubTokenDto

    @GET("/notifications")
    suspend fun getNotifications(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "Accept: application/vnd.github+json"
    ): List<GithubNotificationDto>
}