package com.github.repo.presentation.common

sealed class UiState<out T : Any> {
    object Error : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val value: T) : UiState<T>()
}

inline fun <reified T : Any> UiState<T>.onError(action: () -> Unit) {
    if (this is UiState.Error) {
        action()
    }
}

inline fun <reified T : Any> UiState<T>.onSuccess(action: (data: T) -> Unit) {
    if (this is UiState.Success) {
        action(value)
    }
}

inline fun <reified T : Any> UiState<T>.onLoading(action: () -> Unit) {
    if (this is UiState.Loading) {
        action()
    }
}

