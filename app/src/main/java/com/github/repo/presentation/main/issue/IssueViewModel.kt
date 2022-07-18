package com.github.repo.presentation.main.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.launch

class IssueViewModel(
    private val repository: GithubRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {

    private val token = "token ${tokenSharedPreference.getToken()}"
    private val _items = MutableLiveData(listOf("Open", "Closed", "All"))
    val items: LiveData<List<String>> = _items

    init {
        getIssues()
    }

    private fun getIssues(state: String = "open") = viewModelScope.launch {
        val issues = repository.getIssues(token, state).getOrThrow()
    }

}