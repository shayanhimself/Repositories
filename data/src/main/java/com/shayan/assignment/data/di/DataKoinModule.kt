package com.shayan.assignment.data.di

import com.shayan.assignment.data.repository.GithubRepository
import org.koin.dsl.module

val dataKoinModule = module {

    factory {
        GithubRepository(
            remoteDataSource = get(),
            appDatabase = get(),
        )
    }
}
