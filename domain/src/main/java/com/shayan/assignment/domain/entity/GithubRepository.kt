package com.shayan.assignment.domain.entity

data class GithubRepository(
    val id: Long,
    val name: String,
    val fullName: String,
    val private: Boolean,
    val ownerAvatarUrl: String,
    val visibility: String,
)
