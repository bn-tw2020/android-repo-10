package com.github.repo.presentation.main.notifications

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
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
                Log.d("tester",list.toString())
                list.forEach{
                    Log.d("tester",it.toString())
                }
                _uiState.value = UiState.GetNotifications(list)
            }
            .onFailure {
                it.printStackTrace()
                Log.d("Tester",tokenSharedPreference.getToken())
                _uiState.value = UiState.Error
            }
    }
}