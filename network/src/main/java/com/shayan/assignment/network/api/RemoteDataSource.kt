package com.shayan.assignment.network.api

import com.shayan.assignment.network.ApiConstants.DEFAULT_USER_NAME
import com.shayan.assignment.network.ApiConstants.PAGE_SIZE


class RemoteDataSource(private val githubService: GithubService) {

    suspend fun getRepositories(page: Int) = githubService.getRepositories(
        userName = DEFAULT_USER_NAME,
        page = page,
        size = PAGE_SIZE,
    )

}
