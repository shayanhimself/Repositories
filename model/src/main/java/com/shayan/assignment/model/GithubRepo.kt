package com.shayan.assignment.model

data class GithubRepo(
    val id: Int,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val ownerAvatarUrl: String,
    val visibility: String,
)
