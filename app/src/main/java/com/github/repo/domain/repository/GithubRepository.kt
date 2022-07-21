package com.github.repo.domain.repository

import com.github.repo.domain.model.GithubIssue
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.model.Profile

interface GithubRepository {

    suspend fun getIssues(state: String): Result<List<GithubIssue>>
    suspend fun getNotifications(page: Int): Result<List<Notification>>
    suspend fun getMyProfile(): Result<Profile>
    suspend fun removeNotification(cache: List<String>)
    suspend fun searchRepositories(keyword: String, page: Int): Result<GithubSearch>
}