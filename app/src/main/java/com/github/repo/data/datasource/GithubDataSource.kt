package com.github.repo.data.datasource

import com.github.repo.data.dto.*
import com.github.repo.data.network.GitHubService

class GithubDataSource(private val gitHubService: GitHubService) {

    suspend fun getIssues(token: String, state: String): Result<List<GithubIssueDto>> {
        return runCatching { gitHubService.getIssues(token, state) }
    }

    suspend fun searchRepositories(keyword: String): Result<GithubSearchDto> {
        return runCatching {
            gitHubService.searchRepositories(keyword)
        }
    }

    suspend fun getNotifications(token: String): Result<List<GithubNotificationDto>> =
        runCatching { gitHubService.getNotifications(token) }

    suspend fun getIssueFromNotification(token: String, url: String): Result<GithubIssueDto> =
        runCatching { gitHubService.getIssueFromNotification(token, url) }

    suspend fun removeNotification(token: String, id: String): Result<Unit> =
        runCatching { gitHubService.removeNotification(token, id) }

    suspend fun getMyProfile(token: String): Result<GithubProfileDto> =
        runCatching { gitHubService.getMyProfile(token) }

    suspend fun getStarred(login: String): Result<List<GithubStarredDto>> =
        runCatching { gitHubService.getStarred(login) }

    suspend fun getOrganization(token: String, login: String): Result<List<GithubOrganizationDto>> =
        runCatching { gitHubService.getOrganization(token, login) }
}