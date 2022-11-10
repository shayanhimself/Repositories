package com.shayan.assignment.network.model

import com.shayan.assignment.network.dto.GithubRepoDto

data class ReposResponse(
    val repos: List<GithubRepoDto>,
    val isSuccessful: Boolean,
    val isLastPage: Boolean,
)
