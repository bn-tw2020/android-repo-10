package com.github.repo.data.network

import com.github.repo.data.dto.*
import com.github.repo.presentation.common.RecyclerViewScrollMediator
import retrofit2.http.*

interface GitHubService {

    @FormUrlEncoded
    @POST("login/oauth/access_token")
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): GithubTokenDto

    @GET("user/issues")
    suspend fun getIssues(
        @Query("state") state: String,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
        @Query("page") page: Int = 1,
    ): List<GithubIssueDto>

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
    ): GithubSearchDto

    @GET("/notifications")
    suspend fun getNotifications(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
    ): List<GithubNotificationDto>

    @GET
    suspend fun getIssueFromNotification(
        @Url url: String
    ): GithubIssueDto

    @PATCH("/notifications/threads/{thread_id}")
    suspend fun removeNotification(
        @Path("thread_id") id: String
    )

    @GET("/user")
    suspend fun getMyProfile(): GithubProfileDto

    @GET("/users/{user}/starred")
    suspend fun getStarred(@Path("user") userName: String): List<GithubStarredDto>

    @GET("/users/{login}/orgs")
    suspend fun getOrganization(
        @Path("login") login: String
    ): List<GithubOrganizationDto>
}