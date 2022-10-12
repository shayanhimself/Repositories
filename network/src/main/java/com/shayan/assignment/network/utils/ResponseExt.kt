package com.shayan.assignment.network.utils

import com.shayan.assignment.network.dto.GithubRepoDto
import retrofit2.Response

fun Response<List<GithubRepoDto>>.isLastPage(): Boolean {
    val linkHeader = headers()["link"]
    return linkHeader?.contains("rel=\"first\"") == true && !linkHeader.contains("rel=\"last\"")
}


