package com.github.repo.domain.repository

import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.dto.NotificationDto

interface GithubRepository {

    suspend fun getIssues()
    suspend fun getNotifications(token: String): Result<List<NotificationDto>>
    suspend fun getProfile()
    suspend fun searchRepositories(keyword: String): Result<GithubSearch>
}