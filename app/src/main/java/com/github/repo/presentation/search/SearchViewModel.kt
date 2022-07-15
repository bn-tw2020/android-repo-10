package com.github.repo.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest

class SearchViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    val searchKeyword = MutableStateFlow(BLANK)

    @ExperimentalCoroutinesApi
    @FlowPreview
    val uiState = searchKeyword
        .debounce(300)
        .mapLatest { query ->
            if (query.isBlank()) {
                UiState.Error
            } else {
                val githubSearch = githubRepository.searchRepositories(query).getOrNull()
                githubSearch?.let {
                    UiState.GetRepositories(it)
                } ?: UiState.Error
            }
        }.asLiveData()

    fun clearKeyword() {
        searchKeyword.value = BLANK
    }

    companion object {
        const val BLANK = ""
    }
}