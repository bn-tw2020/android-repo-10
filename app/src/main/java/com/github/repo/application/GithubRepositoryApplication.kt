package com.github.repo.application

import android.app.Application
import com.github.repo.di.dataSourceModule
import com.github.repo.di.networkModule
import com.github.repo.di.repositoryModule
import com.github.repo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubRepositoryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubRepositoryApplication)
            modules(networkModule + dataSourceModule + repositoryModule + viewModelModule)
        }
    }
}