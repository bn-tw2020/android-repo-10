package com.github.repo.presentation.search

import com.github.repo.domain.model.GithubSearch


sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class GetRepositories(val repositories: GithubSearch) : UiState()
}