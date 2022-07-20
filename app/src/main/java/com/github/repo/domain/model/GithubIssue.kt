package com.github.repo.domain.model

data class GithubIssue(
    val number: Int,
    val state: String,
    val createdAt: String,
    val repository: GithubRepo?,
    val title: String
)