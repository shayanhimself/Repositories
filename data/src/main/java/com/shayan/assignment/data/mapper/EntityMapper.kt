package com.shayan.assignment.data.mapper

import com.shayan.assignment.database.entity.GithubRepoEntity
import com.shayan.assignment.model.GithubRepoModel

fun GithubRepoEntity.toModel() = GithubRepoModel(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    isPrivate = this.private,
    ownerAvatarUrl = this.ownerAvatarUrl,
    visibility = this.visibility,
)

fun List<GithubRepoEntity>.toModels(): List<GithubRepoModel> = this.map { it.toModel() }
