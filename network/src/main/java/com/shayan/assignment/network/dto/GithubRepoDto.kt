package com.shayan.assignment.network.dto

import com.google.gson.annotations.SerializedName

data class GithubRepoDto(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val owner: OwnerDto,
    val visibility: String,
)
