package com.github.repo.domain.repository

import com.github.repo.domain.model.GithubSearch

interface GithubRepository {

    suspend fun getIssues()
    suspend fun getNotifications()
    suspend fun getProfile()
    suspend fun searchRepositories(keyword: String): Result<GithubSearch>
}