package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubSearchDto(
    @Json(name = "total_count") val totalCount: Int,
    val items: List<GithubRepoDto>
)