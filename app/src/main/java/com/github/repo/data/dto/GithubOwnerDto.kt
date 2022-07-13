package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubOwnerDto(
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)