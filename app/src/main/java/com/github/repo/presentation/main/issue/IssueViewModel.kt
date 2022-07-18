package com.github.repo.presentation.main.issue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IssueViewModel : ViewModel() {

    private val _items = MutableLiveData(listOf("Open", "Closed", "All"))
    val items: LiveData<List<String>> = _items

}