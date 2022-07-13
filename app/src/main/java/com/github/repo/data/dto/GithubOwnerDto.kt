package com.github.repo.data.dto

import com.github.repo.domain.model.GithubOwner
import com.squareup.moshi.Json

data class GithubOwnerDto(
    val login: String,
    @Json(name = "avatar_url") val avatarUrl: String
)

fun GithubOwnerDto.toGithubOwner(): GithubOwner = GithubOwner(login, avatarUrl)