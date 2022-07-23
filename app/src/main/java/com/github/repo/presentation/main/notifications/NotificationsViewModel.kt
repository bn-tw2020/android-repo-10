package com.github.repo.presentation.main.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.domain.model.Notification
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import com.github.repo.presentation.common.onSuccess
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<Notification>>>()
    val uiState: LiveData<UiState<List<Notification>>> = _uiState
    private val cache = mutableListOf<String>()
    private var prevList = listOf<Notification>()

    init {
        getNotifications(1)
    }

    fun getNotifications(page: Int) = viewModelScope.launch {
        _uiState.value?.onSuccess { it ->
            prevList = it
        }
        _uiState.value = UiState.Loading
        githubRepository.getNotifications(page)
            .onSuccess { data ->
                _uiState.value = UiState.Success(prevList)
                _uiState.value?.onSuccess { it ->
                    val list = it.toMutableList()
                    list.addAll(data)
                    _uiState.value = UiState.Success(list)
                }
            }
            .onFailure { _uiState.value = UiState.Error }
    }

    fun addToRemoveCache(id: String) {
        cache.add(id)
    }

    fun removeNotification() = viewModelScope.launch {
        githubRepository.removeNotification(cache)
    }
}