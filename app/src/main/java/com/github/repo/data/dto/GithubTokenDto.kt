package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubTokenDto(
    @Json(name = "access_token") val accessToken: String,
    @Json(name = "scope") val scope: String,
    @Json(name = "token_type") val tokenType: String
)