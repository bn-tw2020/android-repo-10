package com.github.repo.di

import org.koin.dsl.module

val networkModule = module {
    single { provideMoshi() }
    single { provideMoshiConverterFactory(get()) }
    single { provideOkHttp() }
    single { provideRetrofit(get(), get()) }
    single { provideGithubService(get()) }
}