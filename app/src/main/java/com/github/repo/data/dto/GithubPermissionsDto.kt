package com.github.repo.data.dto

data class GithubPermissionsDto(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)