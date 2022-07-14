package com.github.repo.presentation.main.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
import com.github.repo.domain.dto.NotificationDto
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.launch

class NotificationsViewModel(
    private val githubRepository: GithubRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun getNotifications() = viewModelScope.launch {
        _uiState.value = UiState.Loading
        githubRepository.getNotifications("token ${tokenSharedPreference.getToken()}")
            .onSuccess { list ->
                val dummies = listOf(
                    NotificationDto("https://avatars.githubusercontent.com/u/9919?s=460&v=4","test/repo", "test notification title", 3, "2014-11-07T22:01:45Z",3),
                    NotificationDto("https://avatars.githubusercontent.com/u/9919?s=460&v=4","test/repo", "test notification title", 3, "2014-11-07T22:01:45Z",3),
                    NotificationDto("https://avatars.githubusercontent.com/u/9919?s=460&v=4","test/repo", "test notification title", 3, "2014-11-07T22:01:45Z",3),
                    NotificationDto("https://avatars.githubusercontent.com/u/9919?s=460&v=4","test/repo", "test notification title", 3, "2014-11-07T22:01:45Z",3),
                    NotificationDto("https://avatars.githubusercontent.com/u/9919?s=460&v=4","test/repo", "test notification title", 3, "2014-11-07T22:01:45Z",3),
                )
                _uiState.value = UiState.GetNotifications(dummies)
            }
            .onFailure {
                it.printStackTrace()
                Log.d("Tester",tokenSharedPreference.getToken())
                _uiState.value = UiState.Error
            }
    }
}