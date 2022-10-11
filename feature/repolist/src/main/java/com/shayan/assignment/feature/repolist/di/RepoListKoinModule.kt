package com.shayan.assignment.feature.repolist.di

import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repoListKoinModule = module {
    viewModel {
        RepoListViewModel(githubRepository = get())
    }
}
