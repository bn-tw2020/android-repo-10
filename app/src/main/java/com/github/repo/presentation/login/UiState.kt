package com.github.repo.presentation.login

sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class GetToken(val token: String) : UiState()
}
