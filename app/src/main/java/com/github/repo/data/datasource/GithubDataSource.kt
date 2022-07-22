package com.github.repo.data.datasource

import com.github.repo.data.dto.*
import com.github.repo.data.network.GitHubService

class GithubDataSource(private val gitHubService: GitHubService) {

    suspend fun getIssues(state: String, page: Int): Result<List<GithubIssueDto>> =
        runCatching { gitHubService.getIssues(state, page = page) }

    suspend fun searchRepositories(keyword: String, page: Int): Result<GithubSearchDto> =
        runCatching { gitHubService.searchRepositories(keyword, page = page) }

    suspend fun getNotifications(page: Int): Result<List<GithubNotificationDto>> =
        runCatching { gitHubService.getNotifications(page = page) }

    suspend fun getIssueFromNotification(url: String): Result<GithubIssueDto> =
        runCatching { gitHubService.getIssueFromNotification(url) }

    suspend fun removeNotification(id: String): Result<Unit> =
        runCatching { gitHubService.removeNotification(id) }

    suspend fun getMyProfile(): Result<GithubProfileDto> =
        runCatching { gitHubService.getMyProfile() }

    suspend fun getStarred(login: String): Result<List<GithubStarredDto>> =
        runCatching { gitHubService.getStarred(login) }

    suspend fun getOrganization(login: String): Result<List<GithubOrganizationDto>> =
        runCatching { gitHubService.getOrganization(login) }
}