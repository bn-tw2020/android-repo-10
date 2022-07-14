package com.github.repo.di

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.datasource.LoginDataSource
import com.github.repo.data.datasource.TokenSharedPreference
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataSourceModule = module {
    single { LoginDataSource(get(named("auth_service"))) }
    single { TokenSharedPreference(get()) }
    single { GithubDataSource(get(named("api_service"))) }
}
