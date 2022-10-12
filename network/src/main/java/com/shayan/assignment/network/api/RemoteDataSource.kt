package com.shayan.assignment.network.api

import com.shayan.assignment.network.ApiConstants.DEFAULT_USER_NAME


class RemoteDataSource(private val githubService: GithubService) {

    suspend fun getRepositories(page: Int) = githubService.getRepositories(
        userName = DEFAULT_USER_NAME,
        page = page,
        size = PAGE_SIZE,
    )

    companion object {
        const val PAGE_SIZE = 10
    }

}
