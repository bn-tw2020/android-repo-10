package com.github.repo.data.dto

import com.github.repo.domain.model.Profile
import com.squareup.moshi.Json

data class GithubProfileDto(
    @Json(name = "avatar_url")
    var avatarUrl: String,
    @Json(name = "bio")
    var bio: String?,
    @Json(name = "blog")
    var blog: String?,
    @Json(name = "company")
    var company: String?,
    @Json(name = "created_at")
    var createdAt: String?,
    @Json(name = "email")
    var email: String?,
    @Json(name = "events_url")
    var eventsUrl: String?,
    @Json(name = "followers")
    var followers: Int,
    @Json(name = "followers_url")
    var followersUrl: String?,
    @Json(name = "following")
    var following: Int,
    @Json(name = "following_url")
    var followingUrl: String?,
    @Json(name = "gists_url")
    var gistsUrl: String?,
    @Json(name = "gravatar_id")
    var gravatarId: String?,
    @Json(name = "hireable")
    var hireable: Any?,
    @Json(name = "html_url")
    var htmlUrl: String?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "location")
    var location: String?,
    @Json(name = "login")
    var login: String,
    @Json(name = "name")
    var name: String,
    @Json(name = "node_id")
    var nodeId: String?,
    @Json(name = "organizations_url")
    var organizationsUrl: String?,
    @Json(name = "public_gists")
    var publicGists: Int?,
    @Json(name = "public_repos")
    var publicRepos: Int,
    @Json(name = "received_events_url")
    var receivedEventsUrl: String?,
    @Json(name = "repos_url")
    var reposUrl: String?,
    @Json(name = "site_admin")
    var siteAdmin: Boolean?,
    @Json(name = "starred_url")
    var starredUrl: String?,
    @Json(name = "subscriptions_url")
    var subscriptionsUrl: String?,
    @Json(name = "twitter_username")
    var twitterUsername: Any?,
    @Json(name = "type")
    var type: String?,
    @Json(name = "updated_at")
    var updatedAt: String?,
    @Json(name = "url")
    var url: String?,
    @Json(name = "total_private_repos")
    var totalPrivateRepos: Int,
    @Json(name = "owned_private_repos")
    var ownedPrivateRepos: Int,
)

fun GithubProfileDto.toProfile(starCount: Int): Profile {
    return Profile(
        profileImgUrl = avatarUrl,
        userName = name,
        id = login,
        idontknowthis = "???이거 대체 무슨 내용임??",
        bioDescription = bio,
        blogUrl = blog,
        location = location,
        email = email,
        followerCount = followers,
        followingCount = following,
        repositoryCount = publicRepos + totalPrivateRepos,
        starredCount = starCount
    )
}