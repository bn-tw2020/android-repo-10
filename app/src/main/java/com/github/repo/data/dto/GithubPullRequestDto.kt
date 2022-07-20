package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubPullRequestDto(
    @Json(name = "diff_url") val diffUrl: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "patch_url") val patchUrl: String,
    val url: String
)