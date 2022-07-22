package com.github.repo.domain.model

data class GithubRepository(
    val id: Long,
    val name: String?,
    val fullName: String,
    val owner: GithubOwner,
    val description: String?,
    val language: String?,
    val languageColor: String?,
    val star: Int?
)
