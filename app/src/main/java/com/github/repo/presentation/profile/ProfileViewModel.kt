package com.github.repo.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.github.repo.data.datasource.TokenSharedPreference
import com.github.repo.domain.model.Profile
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.presentation.common.UiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val githubRepository: GithubRepository,
    private val tokenSharedPreference: TokenSharedPreference
) : ViewModel() {

    private val myProfile = MutableSharedFlow<UiState<Profile>>()
    private val token = "token ${tokenSharedPreference.getToken()}"

    @ExperimentalCoroutinesApi
    @FlowPreview
    val uiState = myProfile.asLiveData()

    fun getMyProfile() = viewModelScope.launch {
        myProfile.tryEmit(UiState.Loading)
        githubRepository.getMyProfile(token)
            .onSuccess { myProfile.tryEmit(UiState.Success(it)) }
            .onFailure { myProfile.tryEmit(UiState.Error) }
    }
}