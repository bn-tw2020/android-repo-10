package com.github.repo.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
import com.github.repo.domain.repository.LoginRepository
import com.github.repo.presentation.common.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<String>>()
    val uiState: LiveData<UiState<String>> = _uiState

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error
    }

    fun getAccessToken(code: String) = viewModelScope.launch(coroutineExceptionHandler) {
        _uiState.value = UiState.Loading
        loginRepository.getAccessToken(code)
            .onSuccess {
                tokenSharedPreference.saveToken(it.accessToken)
                _uiState.value = UiState.Success(it.accessToken)
            }
            .onFailure {
                _uiState.value = UiState.Error
            }
    }
}