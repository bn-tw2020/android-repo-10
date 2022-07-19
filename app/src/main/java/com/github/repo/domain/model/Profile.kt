package com.github.repo.domain.model

data class Profile (
    val profileImgUrl: String,
    val userName: String,
    val id: String,
    val bioDescription: String?,
    val blogUrl: String?,
    val location: String?,
    val email: String?,
    val followerCount: Int,
    val followingCount: Int,
    val repositoryCount: Int,
    val organizationCount: Int,
    val starredCount: Int,
)