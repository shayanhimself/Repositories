package com.shayan.assignment.mocked

import com.shayan.assignment.network.api.GithubService
import retrofit2.Response

class GithubServiceMock : GithubService {
    override suspend fun getRepositories(
        userName: String,
        page: Int,
        size: Int
    ) = Response.success(createRepoDtoList(20))
}
