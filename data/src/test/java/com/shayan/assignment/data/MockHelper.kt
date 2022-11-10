package com.shayan.assignment.data

import com.shayan.assignment.database.entity.GithubRepoEntity
import com.shayan.assignment.network.model.ReposResponse


fun createRepoEntities(count: Int): List<GithubRepoEntity> = List(count) { it }.map {
    GithubRepoEntity(id = it)
}

fun createFailResponse() = ReposResponse(
    repos = emptyList(),
    isSuccessful = false,
    isLastPage = false,
)
