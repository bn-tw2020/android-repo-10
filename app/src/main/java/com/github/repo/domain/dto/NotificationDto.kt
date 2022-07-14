package com.github.repo.domain.dto

data class NotificationDto (
    val thumbnailUrl: String,
    val repoName: String,
    val notificationTitle: String,
    val issueNumber: Int,
    val updateTime: String,
    val commentCount: Int,
)