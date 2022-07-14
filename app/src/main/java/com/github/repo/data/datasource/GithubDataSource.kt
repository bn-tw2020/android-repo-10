package com.github.repo.data.datasource

import com.github.repo.data.dto.GithubNotificationDto
import com.github.repo.data.network.GitHubService

class GithubDataSource(private val gitHubService: GitHubService) {

    suspend fun getNotifications(token: String): Result<List<GithubNotificationDto>>
        = runCatching { gitHubService.getNotifications(token) }
}