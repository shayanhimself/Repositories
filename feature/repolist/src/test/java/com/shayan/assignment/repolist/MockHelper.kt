package com.shayan.assignment.repolist

import com.shayan.assignment.model.GithubRepoModel
import com.shayan.assignment.model.Result
import com.shayan.assignment.model.ResultStatus


fun createSuccessResults(
    repos: List<GithubRepoModel>,
    isLastPage: Boolean = false,
) = Result(
    data = repos,
    status = ResultStatus.SUCCESS,
    isLastPage = isLastPage,
)

fun createRepoModels(count: Int): List<GithubRepoModel> = List(count) {it}.map {
    GithubRepoModel(
        id = it,
        name = "",
        fullName = "",
        description = "",
        visibility = "",
        ownerAvatarUrl = "",
        htmlUrl = "",
        isPrivate = false,
    )
}