package com.github.repo.presentation.main.notifications

import android.util.Log
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

    fun getNotifications() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        githubRepository.getNotifications()
            .onSuccess { _uiState.value = UiState.Success(it) }
            .onFailure { _uiState.value = UiState.Error }
    }

    fun removeNotification(id: String) = viewModelScope.launch {
        githubRepository.removeNotification(id)
            .onSuccess { Log.d("Tester", "Success") }
            .onFailure { it.printStackTrace() }
    }
}