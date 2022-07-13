package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubRepoDto(
    val id: Long,
    val name: String,
    val owner: GithubOwnerDto,
    val description: String?,
    val language: String?,
    @Json(name = "stargazers_count") val star: Int
)
