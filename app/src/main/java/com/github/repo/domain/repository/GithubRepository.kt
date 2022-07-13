package com.github.repo.domain.repository

interface GithubRepository {

    suspend fun getIssues()
    suspend fun getNotifications()
    suspend fun getProfile()
    suspend fun searchRepositories()
}