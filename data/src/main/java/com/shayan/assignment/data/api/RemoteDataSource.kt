package com.shayan.assignment.data.api

import com.shayan.assignment.data.DataConstants

class RemoteDataSource(private val githubService: GithubService) {

    suspend fun getRepositories(page: Int) = githubService.getRepositories(
        userName = DataConstants.DEFAULT_USER_NAME,
        page = page,
        size = PAGE_SIZE,
    )

    companion object {
        const val PAGE_SIZE = 10
    }

}
