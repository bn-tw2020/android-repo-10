package com.github.repo.data.dto

import com.github.repo.domain.model.GithubRepo
import com.github.repo.domain.model.colors
import com.squareup.moshi.Json

data class GithubRepoDto(
    val id: Long,
    val name: String,
    @Json(name = "full_name") val fullName: String,
    val owner: GithubOwnerDto,
    val description: String?,
    val language: String?,
    @Json(name = "stargazers_count") val star: Int
)

fun GithubRepoDto.toGithubRepo(): GithubRepo {
    val languageColor = language?.let { colors[it] }
    return GithubRepo(
        id,
        name,
        fullName,
        owner.toGithubOwner(),
        description,
        language,
        languageColor,
        star
    )
}