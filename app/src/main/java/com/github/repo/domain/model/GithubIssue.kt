package com.github.repo.domain.model

data class GithubIssue(
    val number: Int,
    val state: String,
    val createdAt: String,
    val repository: GithubRepository?,
    val title: String
)