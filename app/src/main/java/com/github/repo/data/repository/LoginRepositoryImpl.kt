package com.github.repo.data.repository

import com.github.repo.data.datasource.LoginDataSource
import com.github.repo.data.dto.GithubTokenDto
import com.github.repo.domain.repository.LoginRepository

class LoginRepositoryImpl(private val dataSource: LoginDataSource) : LoginRepository {
    override suspend fun getAccessToken(code: String): Result<GithubTokenDto> {
        return dataSource.getAccessToken(code)
    }
}