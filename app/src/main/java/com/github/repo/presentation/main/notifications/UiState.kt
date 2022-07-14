package com.github.repo.presentation.main.notifications

import com.github.repo.domain.dto.Notification

sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class GetNotifications(val notificationList: List<Notification>) : UiState()
}
