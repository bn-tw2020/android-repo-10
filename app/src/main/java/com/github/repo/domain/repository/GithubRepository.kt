package com.github.repo.domain.repository

import com.github.repo.data.dto.GithubIssueDto
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification

interface GithubRepository {

    suspend fun getIssues(token: String, state: String): Result<List<GithubIssueDto>>
    suspend fun getNotifications(token: String): Result<List<Notification>>
    suspend fun getProfile()
    suspend fun searchRepositories(keyword: String): Result<GithubSearch>
    suspend fun removeNotification(token: String, id: String): Result<Unit>
}