package com.github.repo.data.repository

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.dto.GithubSearchDto
import com.github.repo.domain.repository.GithubRepository

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues() {}
    override suspend fun getNotifications() {}
    override suspend fun getProfile() {}

    override suspend fun searchRepositories(keyword: String): Result<GithubSearchDto> {
        return githubDataSource.searchRepositories(keyword)
    }
}