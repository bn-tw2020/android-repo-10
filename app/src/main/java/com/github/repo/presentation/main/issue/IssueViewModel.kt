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
import kotlinx.coroutines.CoroutineExceptionHandler
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

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = UiState.Error
    }

    fun getIssues(state: String, page: Int = 1) = viewModelScope.launch(coroutineExceptionHandler) {
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

    fun setSelectedPosition(position: Int) {
        _selectedPosition.value = position
    }
}