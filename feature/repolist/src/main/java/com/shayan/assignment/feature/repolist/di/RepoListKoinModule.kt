package com.shayan.assignment.feature.repolist.di

import com.shayan.assignment.feature.repolist.mapper.ErrorMapper
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoListKoinModule = module {
    factory { ErrorMapper(androidContext()) }

    viewModel {
        RepoListViewModel(
            githubRepository = get(),
            errorMapper = get(),
        )
    }
}
