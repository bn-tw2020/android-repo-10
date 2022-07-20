package com.github.repo.presentation.main.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
import com.github.repo.domain.model.GithubIssue
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import com.github.repo.presentation.main.issue.OptionType.OPEN
import kotlinx.coroutines.launch

class IssueViewModel(
    private val repository: GithubRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {

    private val token = "token ${tokenSharedPreference.getToken()}"
    private val _items = MutableLiveData(OptionType.create())
    val items: LiveData<List<String>> = _items

    private var _selectedPosition = MutableLiveData<Int>(OPEN.position)
    val selectedPosition: LiveData<Int> = _selectedPosition

    private val _uiState = MutableLiveData<UiState<List<GithubIssue>>>()
    val uiState: LiveData<UiState<List<GithubIssue>>> = _uiState

    fun getIssues(state: String) = viewModelScope.launch {
        _uiState.value = UiState.Loading
        repository.getIssues(token, state)
            .onSuccess {
                _uiState.value = UiState.Success(it)
            }
            .onFailure { _uiState.value = UiState.Error }
    }

    fun setSelectedPosition(position: Int) {
        _selectedPosition.value = OptionType.value(position) ?: return
    }
}