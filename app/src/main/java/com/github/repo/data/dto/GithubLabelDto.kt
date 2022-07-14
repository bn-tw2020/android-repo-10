package com.github.repo.data.dto


import com.squareup.moshi.Json

data class GithubLabelDto(
    @Json(name = "color")
    val color: String,
    @Json(name = "default")
    val default: Boolean,
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "url")
    val url: String
)