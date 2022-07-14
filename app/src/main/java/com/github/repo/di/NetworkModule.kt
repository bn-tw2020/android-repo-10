package com.github.repo.di

import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkModule = module {
    single { provideMoshi() }
    single { provideMoshiConverterFactory(get()) }
    single { provideOkHttp() }
    single(named("github_auth")) { provideRetrofit(get(), get()) }
    single(named("github_api")) { provideGithubApiRetrofit(get(), get()) }
    single { provideGithubService(get(named("github_auth"))) }
}