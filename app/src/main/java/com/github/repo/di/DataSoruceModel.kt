package com.github.repo.di

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.datasource.LoginDataSource
import com.github.repo.data.datasource.TokenSharedPreference
import org.koin.dsl.module

val dataSourceModule = module {
    single { LoginDataSource(get()) }
    single { TokenSharedPreference(get()) }
    single { GithubDataSource(get()) }
}
