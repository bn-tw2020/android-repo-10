package com.github.repo.presentation.main.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.domain.model.GithubIssue
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import com.github.repo.presentation.common.onSuccess
import com.github.repo.presentation.main.issue.OptionType.OPEN
import kotlinx.coroutines.launch

class IssueViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _items = MutableLiveData(OptionType.create())
    val items: LiveData<List<String>> = _items

    private var _selectedPosition = MutableLiveData<Int>(OPEN.position)
    val selectedPosition: LiveData<Int> = _selectedPosition

    private val _uiState = MutableLiveData<UiState<List<GithubIssue>>>()
    val uiState: LiveData<UiState<List<GithubIssue>>> = _uiState

    private var prevList: List<GithubIssue> = emptyList()
    private var prevState: String = OptionType.OPEN.optionName

    fun getIssues(state: String, page: Int = 1) = viewModelScope.launch {
        if (prevState != state) {
            prevList = emptyList()
            prevState = state
            _uiState.value = UiState.Loading
        }

        _uiState.value?.onSuccess { prevList = it }

        _uiState.value = UiState.Loading
        repository.getIssues(state, page)
            .onSuccess { data ->
                if (prevList.isEmpty()) {
                    _uiState.value = UiState.Success(data)
                    return@launch
                }
                _uiState.value = UiState.Success(prevList)
                _uiState.value?.onSuccess {
                    val list = it.toMutableList()
                    list.addAll(data)
                    _uiState.value = UiState.Success(list.toList())
                }
            }
            .onFailure { _uiState.value = UiState.Error }
    }
    /*
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
        }
}

     */

    fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }
}