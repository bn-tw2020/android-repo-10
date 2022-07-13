package com.github.repo.presentation.search

import com.github.repo.data.dto.GithubSearchDto


sealed class UiState {
    object Error : UiState()
    object Loading : UiState()
    data class GetRepositories(val repositories: GithubSearchDto) : UiState()
}