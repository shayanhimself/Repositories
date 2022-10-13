package com.shayan.assignment.mocked

import com.shayan.assignment.network.dto.GithubRepoDto
import com.shayan.assignment.network.dto.OwnerDto


fun createRepoDtoList(count: Int): List<GithubRepoDto> = List(count) {it}.map { number ->
    GithubRepoDto(
        id= number,
        name = "Repo $number",
        fullName = "abn/Repo$number",
        description = "This is a very cool repo, I swear! This is a very cool repo, I swear!",
        owner = OwnerDto(avatarUrl = "https://avatars.githubusercontent.com/u/15876397?v=4"),
        visibility = "public",
        htmlUrl = "https://github.com/abnamrocoesd/airflow",
        private = false,
    )
}
