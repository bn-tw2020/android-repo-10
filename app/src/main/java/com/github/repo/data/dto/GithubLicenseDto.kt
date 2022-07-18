package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubLicenseDto(
    @Json(name = "html_url") val htmlUrl: String?,
    val key: String,
    val name: String,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "spdx_id") val spdxId: String,
    val url: String
)