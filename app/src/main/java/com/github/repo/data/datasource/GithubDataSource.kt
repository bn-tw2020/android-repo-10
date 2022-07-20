package com.github.repo.data.datasource

import com.github.repo.data.dto.*
import com.github.repo.data.network.GitHubService

class GithubDataSource(private val gitHubService: GitHubService) {

    suspend fun getIssues(state: String): Result<List<GithubIssueDto>> {
        return runCatching { gitHubService.getIssues(state) }
    }

    suspend fun searchRepositories(keyword: String): Result<GithubSearchDto> {
        return runCatching {
            gitHubService.searchRepositories(keyword)
        }
    }

    suspend fun getNotifications(): Result<List<GithubNotificationDto>> =
        runCatching { gitHubService.getNotifications() }

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