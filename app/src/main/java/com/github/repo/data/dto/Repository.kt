package com.github.repo.data.dto

import com.squareup.moshi.Json

data class Repository(
    @Json(name = "archive_url") val archiveUrl: String,
    @Json(name = "assignees_url") val assigneesUrl: String,
    @Json(name = "blobs_url") val blobsUrl: String,
    @Json(name = "branches_url") val branchesUrl: String,
    @Json(name = "collaborators_url") val collaboratorsUrl: String,
    @Json(name = "comments_url") val commentsUrl: String,
    @Json(name = "commits_url") val commitsUrl: String,
    @Json(name = "compare_url") val compareUrl: String,
    @Json(name = "contents_url") val contentsUrl: String,
    @Json(name = "contributors_url") val contributorsUrl: String,
    @Json(name = "deployments_url") val deploymentsUrl: String,
    @Json(name = "description") val description: String,
    @Json(name = "downloads_url") val downloadsUrl: String,
    @Json(name = "events_url") val eventsUrl: String,
    @Json(name = "for") val fork: Boolean,
    @Json(name = "forks_url") val forksUrl: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "git_commits_url") val gitCommitsUurl: String,
    @Json(name = "git_refs_url") val gitRefsUrl: String,
    @Json(name = "git_tags_url") val gitTagsUrl: String,
    @Json(name = "git_url") val gitUrl: String,
    @Json(name = "hooks_url") val hooksUrl: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "id") val id: Int,
    @Json(name = "issue_comment_url") val issueCommentUrl: String,
    @Json(name = "issue_events_url") val issueEventsUrl: String,
    @Json(name = "issues_url") val issuesUrl: String,
    @Json(name = "keys_url") val keysUrl: String,
    @Json(name = "labels_url") val labelsUrl: String,
    @Json(name = "languages_url") val languagesUrl: String,
    @Json(name = "merges_url") val mergesUrl: String,
    @Json(name = "milestones_url") val milestonesUrl: String,
    @Json(name = "name") val name: String,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "notifications_url") val notificationsUrl: String,
    @Json(name = "owner") val owner: GithubOwnerDto,
    @Json(name = "private") val private: Boolean,
    @Json(name = "pulls_url") val pullsUrl: String,
    @Json(name = "releases_url") val releasesUrl: String,
    @Json(name = "ssh_url") val sshUrl: String,
    @Json(name = "stargazers_url") val stargazersUrl: String,
    @Json(name = "statuses_url") val statusesUrl: String,
    @Json(name = "subscribers_url") val subscribersUrl: String,
    @Json(name = "subscription_url") val subscriptionUrl: String,
    @Json(name = "tags_url") val tagsUrl: String,
    @Json(name = "teams_url") val teamsUrl: String,
    @Json(name = "trees_url") val treesUrl: String,
    @Json(name = "url") val url: String
)