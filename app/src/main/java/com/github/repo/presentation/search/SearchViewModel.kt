package com.github.repo.presentation.search

import android.util.Log
import androidx.lifecycle.*
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.repository.GithubRepository
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
        Log.d("success:nextPage", page.toString())
        _uiState.value?.onSuccess { it ->
            prevList = it
        }
        _uiState.value = UiState.Loading
        githubRepository.searchRepositories(searchKeyword.value, page)
            .onSuccess { data ->
                Log.d("success:nextPage", data.toString())
                _uiState.value = UiState.Success(prevList)
                _uiState.value?.onSuccess { it ->
                    Log.d("success:nextPage", it.toString())
                    val list = it.items.toMutableList()
                    list.addAll(data.items)
                    _uiState.value = UiState.Success(it.copy(items = list))
                }
            }
            .onFailure {
                Log.d("Tester", it.toString())
                _uiState.value = UiState.Error
            }
    }

    companion object {
        const val BLANK = ""
    }
}