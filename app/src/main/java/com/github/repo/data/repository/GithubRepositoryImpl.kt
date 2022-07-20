package com.github.repo.data.repository

import android.util.Log
import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.dto.toGithubIssue
import com.github.repo.data.dto.toGithubSearch
import com.github.repo.domain.model.GithubIssue
import com.github.repo.data.dto.toProfile
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.model.Profile
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.*

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues(token: String, state: String): Result<List<GithubIssue>> {
        val issues = githubDataSource.getIssues(token, state).getOrDefault(emptyList())
        return runCatching { issues.map { it.toGithubIssue() } }
    }

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

    override suspend fun getMyProfile(token: String): Result<Profile> {
        val data = githubDataSource.getMyProfile(token).getOrThrow()
        val starJob = CoroutineScope(Dispatchers.IO)
            .async { githubDataSource.getStarred(data.login).getOrThrow().count() }
        val organJob = CoroutineScope(Dispatchers.IO)
            .async { githubDataSource.getOrganization(token, data.login).getOrThrow().count() }

        val starredCount = starJob.await()
        val organizationCount = organJob.await()

        return runCatching { data.toProfile(organCount = organizationCount, starCount = starredCount) }
    }

    override suspend fun searchRepositories(keyword: String): Result<GithubSearch> {
        return githubDataSource.searchRepositories(keyword).map {
            it.toGithubSearch()
        }
    }
}