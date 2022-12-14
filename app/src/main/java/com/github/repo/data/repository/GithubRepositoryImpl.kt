package com.github.repo.data.repository

import com.github.repo.data.datasource.GithubDataSource
import com.github.repo.data.dto.toGithubIssue
import com.github.repo.data.dto.toGithubSearch
import com.github.repo.data.dto.toProfile
import com.github.repo.domain.model.GithubIssue
import com.github.repo.domain.model.GithubSearch
import com.github.repo.domain.model.Notification
import com.github.repo.domain.model.Profile
import com.github.repo.domain.repository.GithubRepository
import kotlinx.coroutines.*

class GithubRepositoryImpl(private val githubDataSource: GithubDataSource) : GithubRepository {

    override suspend fun getIssues(state: String, page: Int): Result<List<GithubIssue>> {
        val issues = githubDataSource.getIssues(state, page = page).getOrThrow()
        return runCatching { issues.map { it.toGithubIssue() } }
    }

    override suspend fun getNotifications(page: Int): Result<List<Notification>> {
        val notificationList = githubDataSource.getNotifications(page).getOrThrow()

        val result = withContext(Dispatchers.IO) {
            val issueList = notificationList.map {
                async {
                    githubDataSource.getIssueFromNotification(it.subject.url).getOrThrow()
                }
            }.awaitAll()
            notificationList.zip(issueList) { notification, issue ->
                Notification(
                    thumbnailUrl = notification.repository.owner.avatarUrl,
                    repoName = notification.repository.fullName,
                    notificationTitle = notification.subject.title,
                    issueNumber = issue.number,
                    updateTime = notification.updatedAt,
                    commentCount = issue.comments,
                    threadId = notification.id
                )
            }
        }
        return runCatching { result.sortedByDescending { it.updateTime } }
    }

    override suspend fun removeNotification(cache: List<String>) {
        CoroutineScope(Dispatchers.IO).launch {
            cache.forEach {
                launch { githubDataSource.removeNotification(it) }
            }
        }
    }

    override suspend fun getMyProfile(): Result<Profile> {
        val data = githubDataSource.getMyProfile().getOrThrow()
        val starJob = CoroutineScope(Dispatchers.IO)
            .async { githubDataSource.getStarred(data.login).getOrThrow().count() }
        val organJob = CoroutineScope(Dispatchers.IO)
            .async { githubDataSource.getOrganization(data.login).getOrThrow().count() }

        val starredCount = starJob.await()
        val organizationCount = organJob.await()

        return runCatching {
            data.toProfile(
                organCount = organizationCount,
                starCount = starredCount
            )
        }
    }

    override suspend fun searchRepositories(keyword: String, page: Int): Result<GithubSearch> {
        return githubDataSource.searchRepositories(keyword, page).map {
            it.toGithubSearch()
        }
    }
}