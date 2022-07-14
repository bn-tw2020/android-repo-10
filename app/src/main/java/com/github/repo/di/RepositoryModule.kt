package com.github.repo.di

import com.github.repo.data.repository.GithubRepositoryImpl
import com.github.repo.data.repository.LoginRepositoryImpl
import com.github.repo.domain.repository.GithubRepository
import com.github.repo.domain.repository.LoginRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
}