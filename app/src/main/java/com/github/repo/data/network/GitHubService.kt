package com.github.repo.data.network

import com.github.repo.data.dto.*
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


    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): GithubSearchDto

    @GET("/notifications")
    suspend fun getNotifications(
        @Header("Authorization") token: String,
        @Header("Accept") accept: String = "Accept: application/vnd.github+json",
        @Query("per_page") perPage: Int = 20
    ): List<GithubNotificationDto>

    @GET
    suspend fun getIssueFromNotification(
        @Header("Authorization") token: String,
        @Url url: String
    ): GithubIssueDto

    @PATCH("/notifications/threads/{thread_id}")
    suspend fun removeNotification(
        @Header("Authorization") token: String,
        @Path("thread_id") id: String
    )

    @GET("/user")
    suspend fun getMyProfile(@Header("Authorization") token: String): GithubProfileDto

    @GET("/users/{user}/starred")
    suspend fun getStarred(@Path("user") userName: String): List<GithubStarredDto>
}