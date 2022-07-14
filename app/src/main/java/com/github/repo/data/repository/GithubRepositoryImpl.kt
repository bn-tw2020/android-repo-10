package com.github.repo.data.repository

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.domain.dto.NotificationDto
import com.github.repo.domain.repository.GithubRepository

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues() {}
    override suspend fun getNotifications(token: String): Result<List<NotificationDto>> =
        runCatching {
            val notificationList = mutableListOf<NotificationDto>()
            githubDataSource.getNotifications(token)
                .onFailure { throw it }
                .onSuccess { list ->
                    list.forEach {
                        notificationList.add(
                            NotificationDto(
                                thumbnailUrl = it.repository.owner.avatar_url,
                                repoName = it.repository.full_name,
                                notificationTitle = it.subject.title,
                                issueNumber = 3,
                                updateTime = it.updated_at,
                                commentCount = 2,
                            )
                        )
                    }
                }
            notificationList
        }

    override suspend fun getProfile() {}
    override suspend fun searchRepositories() {}
}