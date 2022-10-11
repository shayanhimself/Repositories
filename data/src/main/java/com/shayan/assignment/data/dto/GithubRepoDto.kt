package com.shayan.assignment.data.dto

import com.google.gson.annotations.SerializedName
import com.shayan.assignment.database.entity.GithubRepoEntity

data class GithubRepoDto(
    val id: Int,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val owner: OwnerDto,
    val visibility: String,
) {
    fun toEntity() = GithubRepoEntity(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        private = this.private,
        ownerAvatarUrl = this.owner.avatarUrl,
        visibility = this.visibility,
    )
}
