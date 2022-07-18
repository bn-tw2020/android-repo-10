package com.github.repo.domain.repository

import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.model.Profile

interface GithubRepository {

    suspend fun getIssues()
    suspend fun getNotifications(token: String): Result<List<Notification>>
    suspend fun getMyProfile(token: String): Result<Profile>
    suspend fun searchRepositories(keyword: String): Result<GithubSearch>
    suspend fun removeNotification(token: String, id: String): Result<Unit>
}