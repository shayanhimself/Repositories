package com.shayan.assignment.data.di

import com.shayan.assignment.data.GithubRepoRemoteMediator
import com.shayan.assignment.data.repository.GithubRepository
import org.koin.dsl.module

val dataKoinModule = module {
    factory {
        GithubRepoRemoteMediator(
            githubService = get(),
            appDatabase = get(),
        )
    }

    factory {
        GithubRepository(
            githubRepoRemoteMediator = get(),
            githubRepoDao = get(),
        )
    }
}
