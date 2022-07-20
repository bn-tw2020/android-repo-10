package com.github.repo.data.dto

import com.github.repo.domain.model.GithubIssue
import com.squareup.moshi.Json

data class GithubIssueDto(
    @Json(name = "active_lock_reason") val activeLockReason: String?,
    @Json(name = "assignee") val assignee: GithubAssigneeDto?,
    @Json(name = "assignees") val assignees: List<GithubAssigneeDto>,
    @Json(name = "author_association") val authorAssociation: String,
    @Json(name = "body") val body: String,
    @Json(name = "closed_at") val closedAt: String?,
    @Json(name = "closed_by") val closedBy: Any?,
    @Json(name = "comments") val comments: Int,
    @Json(name = "comments_url") val commentsUrl: String,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "events_url") val eventsUrl: String?,
    @Json(name = "html_url") val htmlUrl: String?,
    @Json(name = "id") val id: Int,
    @Json(name = "labels") val labels: List<GithubLabelDto>,
    @Json(name = "labels_url") val labelsUrl: String?,
    @Json(name = "locked") val locked: Boolean,
    @Json(name = "milestone") val milestone: GithubMilestoneDto?,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "performed_via_github_app") val performedViaGithubApp: String?,
    @Json(name = "reactions") val reactions: GithubReactionsDto?,
    @Json(name = "state_reason") val stateReason: String?,
    @Json(name = "timeline_url") val timelineUrl: String?,
    @Json(name = "number") val number: Int,
    @Json(name = "pull_request") val pullRequest: GithubPullRequestDto?,
    @Json(name = "repository") val repository: GithubRepositoryDto?,
    @Json(name = "repository_url") val repositoryUrl: String?,
    @Json(name = "state") val state: String,
    @Json(name = "title") val title: String,
    @Json(name = "updated_at") val updatedAt: String,
    @Json(name = "url") val url: String,
    @Json(name = "user") val user: GithubUserDto
)

fun GithubIssueDto.toGithubIssue(): GithubIssue =
    GithubIssue(number, state, createdAt, repository?.toGithubRepo(), title)