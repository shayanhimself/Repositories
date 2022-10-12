package com.shayan.assignment.data.mapper

import com.shayan.assignment.data.dto.GithubRepoDto
import com.shayan.assignment.database.entity.GithubRepoEntity

fun GithubRepoDto.toEntity(page: Int, index: Int) = GithubRepoEntity(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    private = this.private,
    ownerAvatarUrl = this.owner.avatarUrl,
    visibility = this.visibility,
    page = page,
    orderIndex = page * index
)
