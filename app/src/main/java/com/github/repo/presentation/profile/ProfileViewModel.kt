package com.github.repo.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.repo.domain.model.Profile
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val myProfile = MutableSharedFlow<UiState<Profile>>()

    @ExperimentalCoroutinesApi
    @FlowPreview
    val uiState = myProfile.asLiveData()

    init {
        getMyProfile()
    }

    private fun getMyProfile() = viewModelScope.launch {
        myProfile.emit(UiState.Loading)
        githubRepository.getMyProfile()
            .onSuccess { myProfile.emit(UiState.Success(it)) }
            .onFailure { myProfile.emit(UiState.Error) }
    }
}