package com.shayan.assignment.network.api

import com.shayan.assignment.network.ApiConstants.DEFAULT_USER_NAME
import com.shayan.assignment.network.ApiConstants.PAGE_SIZE
import com.shayan.assignment.network.model.ReposResponse
import com.shayan.assignment.network.utils.isLastPage


class RemoteDataSource(private val githubService: GithubService) {

    suspend fun getRepositories(page: Int): ReposResponse {
        val retrofitResponse = githubService.getRepositories(
            userName = DEFAULT_USER_NAME,
            page = page,
            size = PAGE_SIZE,
        )

        val repos = if (retrofitResponse.isSuccessful && retrofitResponse.body() != null) {
            retrofitResponse.body()!!
        } else {
            null
        }

        val isSuccessful = repos != null

        return ReposResponse(
            repos = repos ?: emptyList(),
            isSuccessful = isSuccessful,
            isLastPage = retrofitResponse.isLastPage(),
        )
    }
}
