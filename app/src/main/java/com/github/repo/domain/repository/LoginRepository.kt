package com.github.repo.domain.repository

import com.github.repo.data.dto.GithubTokenDto

interface LoginRepository {

    suspend fun getAccessToken(code: String): Result<GithubTokenDto>
}