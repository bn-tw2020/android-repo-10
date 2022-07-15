package com.github.repo.data.datasource

import com.github.repo.config.CLIENT_ID
import com.github.repo.config.CLIENT_SECRETS
import com.github.repo.data.dto.GithubTokenDto
import com.github.repo.data.network.GitHubService

class LoginDataSource(private val gitHubService: GitHubService) {

    suspend fun getAccessToken(code: String): Result<GithubTokenDto> {
        return runCatching {
            gitHubService.getAccessToken(CLIENT_ID, CLIENT_SECRETS, code)
        }
    }
}