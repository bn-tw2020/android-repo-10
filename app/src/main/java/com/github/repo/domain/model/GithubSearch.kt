package com.github.repo.domain.model

data class GithubSearch(
    val totalCount: Int,
    val items: List<GithubRepo>
)