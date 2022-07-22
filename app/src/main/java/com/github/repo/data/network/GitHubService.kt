package com.github.repo.data.network

import com.github.repo.data.dto.*
import com.github.repo.presentation.common.Constant.API_ACCESS_TOKEN
import com.github.repo.presentation.common.Constant.API_SEARCH_REPOSITORY
import com.github.repo.presentation.common.Constant.API_USER
import com.github.repo.presentation.common.Constant.API_USER_ISSUES
import com.github.repo.presentation.common.Constant.API_USER_NOTIFICATIONS
import com.github.repo.presentation.common.Constant.API_USER_ORGANIZATION
import com.github.repo.presentation.common.Constant.API_USER_REMOVE_NOTIFICATION
import com.github.repo.presentation.common.Constant.API_USER_STAR
import com.github.repo.presentation.common.RecyclerViewScrollMediator
import retrofit2.http.*

interface GitHubService {

    @FormUrlEncoded
    @POST(API_ACCESS_TOKEN)
    suspend fun getAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("code") code: String
    ): GithubTokenDto

    @GET(API_USER_ISSUES)
    suspend fun getIssues(
        @Query("state") state: String,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
        @Query("page") page: Int = 1,
    ): List<GithubIssueDto>

    @GET(API_SEARCH_REPOSITORY)
    suspend fun searchRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
    ): GithubSearchDto

    @GET(API_USER_NOTIFICATIONS)
    suspend fun getNotifications(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = RecyclerViewScrollMediator.perPage,
    ): List<GithubNotificationDto>

    @GET
    suspend fun getIssueFromNotification(
        @Url url: String
    ): GithubIssueDto

    @PATCH(API_USER_REMOVE_NOTIFICATION)
    suspend fun removeNotification(
        @Path("thread_id") id: String
    )

    @GET(API_USER)
    suspend fun getMyProfile(): GithubProfileDto

    @GET(API_USER_STAR)
    suspend fun getStarred(
        @Path("user") userName: String
    ): List<GithubStarredDto>

    @GET(API_USER_ORGANIZATION)
    suspend fun getOrganization(
        @Path("login") login: String
    ): List<GithubOrganizationDto>
}