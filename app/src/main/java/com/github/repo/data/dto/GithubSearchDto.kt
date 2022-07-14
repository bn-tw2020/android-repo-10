package com.github.repo.data.dto

import com.github.repo.domain.model.GithubSearch
import com.squareup.moshi.Json

data class GithubSearchDto(
    @Json(name = "total_count") val totalCount: Int,
    val items: List<GithubRepoDto>
)

fun GithubSearchDto.toGithubSearch(): GithubSearch {
    val githubRepo = items.map { it.toGithubRepo() }
    return GithubSearch(totalCount, githubRepo)
}