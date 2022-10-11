package com.shayan.assignment.feature.repolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.database.entity.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

class RepoListViewModel(
    githubRepository: GithubRepository,
) : ViewModel() {
    val reposList: Flow<PagingData<GithubRepoEntity>> = githubRepository.getGithubRepos()
}
