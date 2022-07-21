package com.github.repo.data.dto

import com.github.repo.domain.model.GithubSearch
import com.squareup.moshi.Json

data class GithubSearchDto(
    @Json(name = "total_count") val totalCount: Int,
    val items: List<GithubRepositoryDto>
)

fun GithubSearchDto.toGithubSearch(): GithubSearch {
    val repositories = items.map { it.toGithubRepository() }
    return GithubSearch(totalCount, repositories)
}