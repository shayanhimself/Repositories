package com.shayan.assignment.data

import com.shayan.assignment.database.entity.GithubRepoEntity
import com.shayan.assignment.network.dto.GithubRepoDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response


fun createRepoEntities(count: Int): List<GithubRepoEntity> = List(count) { it }.map {
    GithubRepoEntity(id = it)
}

fun createFailResponse(): Response<List<GithubRepoDto>> = Response.error(
    403,
    "{}".toResponseBody("application/json".toMediaTypeOrNull()),
)
