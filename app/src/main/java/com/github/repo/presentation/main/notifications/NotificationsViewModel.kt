package com.github.repo.presentation.main.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.domain.model.Notification
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Notification>>>()
    val uiState: LiveData<UiState<List<Notification>>> = _uiState
    private val cache = mutableListOf<String>()

    fun getNotifications(page: Int) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        githubRepository.getNotifications(page)
            .onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error }
    }

    fun addToRemoveCache(id: String) {
        cache.add(id)
    }

    fun removeNotification() = viewModelScope.launch {
        githubRepository.removeNotification(cache)
    }
}