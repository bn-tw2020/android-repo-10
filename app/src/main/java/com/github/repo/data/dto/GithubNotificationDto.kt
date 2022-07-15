package com.github.repo.data.dto

import com.squareup.moshi.Json

data class GithubNotificationDto(
    @Json(name = "id") val id: String,
    @Json(name = "last_read_at") val lastReadAt: String?,
    @Json(name = "reason") val reason: String?,
    @Json(name = "repository") val repository: GithubRepositoryDto,
    @Json(name = "subject") val subject: GithubSubjectDto,
    @Json(name = "subscription_url") val subscriptionUrl: String?,
    @Json(name = "unread") val unread: Boolean,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "url") val url: String
)