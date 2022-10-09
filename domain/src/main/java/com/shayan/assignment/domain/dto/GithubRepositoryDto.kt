package com.shayan.assignment.domain.dto

import com.google.gson.annotations.SerializedName
import com.shayan.assignment.domain.entity.GithubRepository

data class GithubRepositoryDto(
    val id: Long,
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    val private: Boolean,
    val owner: Owner,
    val visibility: String,
) {
    fun toEntity() = GithubRepository(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        private = this.private,
        ownerAvatarUrl = this.owner.avatarUrl,
        visibility = this.visibility,
    )
}
