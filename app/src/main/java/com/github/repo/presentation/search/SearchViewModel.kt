package com.github.repo.presentation.search

import androidx.lifecycle.*
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.Constant.BLANK
import com.github.repo.presentation.common.UiState
import com.github.repo.presentation.common.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

class SearchViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    val searchKeyword = MutableStateFlow(BLANK)
    lateinit var prevList: GithubSearch

    private val _uiState = searchKeyword
        .debounce(300)
        .mapLatest { query ->
            if (query.isBlank()) {
                UiState.Error
            } else {

                val githubSearch =
                    githubRepository.searchRepositories(query, 1).getOrNull()
                githubSearch?.let {
                    UiState.Success(it)
                } ?: UiState.Error
            }
        }.asLiveData() as MutableLiveData
    val uiState: LiveData<UiState<GithubSearch>> = _uiState

    fun clearKeyword() {
        searchKeyword.value = BLANK
    }

    fun getNextPage(page: Int) = viewModelScope.launch {
        _uiState.value?.onSuccess { it ->
            prevList = it
        }
        _uiState.value = UiState.Loading
        githubRepository.searchRepositories(searchKeyword.value, page)
            .onSuccess { data ->
                _uiState.value = UiState.Success(prevList)
                _uiState.value?.onSuccess { it ->
                    val list = it.items.toMutableList()
                    list.addAll(data.items)
                    _uiState.value = UiState.Success(it.copy(items = list))
                }
            }
            .onFailure {
                _uiState.value = UiState.Error
            }
    }
}