package com.github.repo.data.dto


import com.squareup.moshi.Json

data class GithubReactionsDto(
    @Json(name = "confused")
    val confused: Int,
    @Json(name = "eyes")
    val eyes: Int,
    @Json(name = "heart")
    val heart: Int,
    @Json(name = "hooray")
    val hooray: Int,
    @Json(name = "laugh")
    val laugh: Int,
    @Json(name = "rocket")
    val rocket: Int,
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "url")
    val url: String?,
    @Json(name = "+1")
    val plus: Int,
    @Json(name = "-1")
    val minus: Int
)