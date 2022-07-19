package com.github.repo.data.repository

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.dto.toGithubSearch
import com.github.repo.data.dto.toProfile
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.model.Profile
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues() {}

    override suspend fun getNotifications(token: String): Result<List<Notification>> {
        val updatedNotification = mutableListOf<Notification>()
        val notificationList = githubDataSource.getNotifications(token).getOrThrow()
        return withContext(CoroutineScope(Dispatchers.Main.immediate).coroutineContext) {
            notificationList.forEach { notification ->
                launch {
                    val issue =
                        githubDataSource.getIssueFromNotification(token, notification.subject.url)
                            .getOrThrow()
                    updatedNotification.add(
                        Notification(
                            thumbnailUrl = notification.repository.owner.avatarUrl,
                            repoName = notification.repository.fullName,
                            notificationTitle = notification.subject.title,
                            issueNumber = issue.number,
                            updateTime = notification.updatedAt,
                            commentCount = issue.comments,
                            threadId = notification.id
                        )
                    )
                }
            }
            runCatching { updatedNotification }
        }
    }

    override suspend fun removeNotification(token: String, id: String): Result<Unit> =
        githubDataSource.removeNotification(token, id)

    override suspend fun getMyProfile(token: String): Result<Profile>{
        val data = githubDataSource.getMyProfile(token).getOrThrow()
        val starredCount = githubDataSource.getStarred(data.name).getOrThrow().count()
        val organizationCount = githubDataSource.getOrganization(token, data.name).getOrThrow().count()

        return runCatching{ data.toProfile(organCount = organizationCount, starCount = starredCount) }
    }

    override suspend fun searchRepositories(keyword: String): Result<GithubSearch> {
        return githubDataSource.searchRepositories(keyword).map {
            it.toGithubSearch()
        }
    }
}