package com.github.repo.presentation.main.notifications

import com.github.repo.domain.dto.NotificationDto

sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class GetNotifications(val notificationList: List<NotificationDto>) : UiState()
}
