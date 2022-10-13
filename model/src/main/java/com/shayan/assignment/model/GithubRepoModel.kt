package com.shayan.assignment.model

data class GithubRepoModel(
    val id: Int,
    val name: String,
    val fullName: String,
    val isPrivate: Boolean,
    val ownerAvatarUrl: String,
    val visibility: String,
    val description: String?,
    val htmlUrl: String,
)
