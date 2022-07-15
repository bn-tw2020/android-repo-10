package com.github.repo.di

import com.github.repo.config.GITHUB_API
import com.github.repo.config.GITHUB_OAUTH
import com.github.repo.data.network.GitHubService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun provideMoshi(): Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
    MoshiConverterFactory.create(moshi)

fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder().apply {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
}.build()

fun provideRetrofit(
    moshiConverterFactory: MoshiConverterFactory,
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(GITHUB_OAUTH)
    .addConverterFactory(moshiConverterFactory)
    .client(okHttpClient)
    .build()

fun provideGithubApiRetrofit(
    moshiConverterFactory: MoshiConverterFactory,
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
    .baseUrl(GITHUB_API)
    .addConverterFactory(moshiConverterFactory)
    .client(okHttpClient)
    .build()

fun provideGithubService(retrofit: Retrofit): GitHubService =
    retrofit.create(GitHubService::class.java)

fun provideGithubApi(retrofit: Retrofit): GitHubService =
    retrofit.create(GitHubService::class.java)