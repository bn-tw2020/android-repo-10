package com.github.repo.data.repository

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.dto.toGithubSearch
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.repository.GithubRepository

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues() {}

    override suspend fun getNotifications(token: String): Result<List<Notification>> =
        runCatching {
            val notificationList = mutableListOf<Notification>()
            githubDataSource.getNotifications(token)
                .onFailure { throw it }
                .onSuccess { list ->
                    list.forEach {
                        githubDataSource.getIssueFromNotification(token, it.subject.url)
                            .onFailure { fail -> throw fail }
                            .onSuccess { dto ->
                                notificationList.add(
                                    Notification(
                                        thumbnailUrl = it.repository.owner.avatarUrl,
                                        repoName = it.repository.fullName,
                                        notificationTitle = it.subject.title,
                                        issueNumber = dto.number,
                                        updateTime = it.updatedAt,
                                        commentCount = dto.comments,
                                        threadId = it.id
                                    )
                                )
                            }
                    }
                }
            notificationList
        }

    override suspend fun removeNotification(token: String, id: String): Result<Unit> =
        githubDataSource.removeNotification(token, id)

    override suspend fun getProfile() {}

    override suspend fun searchRepositories(keyword: String): Result<GithubSearch> {
        return githubDataSource.searchRepositories(keyword).map {
            it.toGithubSearch()
        }
    }
}