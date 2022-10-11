package com.shayan.assignment.data.mapper

import com.shayan.assignment.database.entity.GithubRepoEntity
import com.shayan.assignment.model.GithubRepo

fun GithubRepoEntity.toModel() = GithubRepo(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    private = this.private,
    ownerAvatarUrl = this.ownerAvatarUrl,
    visibility = this.visibility,
)

fun List<GithubRepoEntity>.toModels(): List<GithubRepo> = this.map { it.toModel() }
