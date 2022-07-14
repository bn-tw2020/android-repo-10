package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubSubjectDto(
    @Json(name = "latest_comment_url") val latestCommentUrl: String,
    @Json(name = "title") val title: String,
    @Json(name = "type") val type: String,
    @Json(name = "url") val url: String
)