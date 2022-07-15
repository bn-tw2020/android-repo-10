package com.github.repo.domain.model

data class Notification (
    val thumbnailUrl: String,
    val repoName: String,
    val notificationTitle: String,
    val issueNumber: Int,
    val updateTime: String,
    val commentCount: Int,
    val threadId: String,
)