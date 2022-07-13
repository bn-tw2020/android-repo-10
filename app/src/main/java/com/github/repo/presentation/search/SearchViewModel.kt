package com.github.repo.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.launch

class SearchViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun searchRepositories(keyword: String) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        githubRepository.searchRepositories(keyword)
            .onSuccess {
                _uiState.value = UiState.GetRepositories(it)
            }
            .onFailure {
                _uiState.value = UiState.Error
            }
    }

}