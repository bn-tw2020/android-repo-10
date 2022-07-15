package com.github.repo.data.datasource

import com.github.repo.data.dto.GithubIssueDto
import com.github.repo.data.dto.GithubSearchDto
import com.github.repo.data.dto.GithubNotificationDto
import com.github.repo.data.network.GitHubService

class GithubDataSource(private val gitHubService: GitHubService) {

    suspend fun searchRepositories(keyword: String): Result<GithubSearchDto> {
        return runCatching {
            gitHubService.searchRepositories(keyword)
        }
    }

    suspend fun getNotifications(token: String): Result<List<GithubNotificationDto>>
        = runCatching { gitHubService.getNotifications(token) }

    suspend fun getIssueFromNotification(url: String): Result<GithubIssueDto>
            = runCatching { gitHubService.getIssueFromNotification(url) }

    suspend fun removeNotification(id: String): Result<Unit>
            = runCatching { gitHubService.removeNotification(id) }
}