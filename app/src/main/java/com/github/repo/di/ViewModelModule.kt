package com.github.repo.di

import com.github.repo.presentation.login.LoginViewModel
import com.github.repo.presentation.main.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { NotificationsViewModel(get(), get()) }
}