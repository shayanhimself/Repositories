package com.shayan.assignment.network.dto

import com.google.gson.annotations.SerializedName

data class OwnerDto(
    @SerializedName("avatar_url") val avatarUrl: String,
)
