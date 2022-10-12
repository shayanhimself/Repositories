package com.shayan.assignment.feature.repolist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.data.repository.GithubRepository.Companion.INITIAL_PAGE
import kotlinx.coroutines.launch

class RepoListViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    sealed class ViewAction {
        object OnEndOfListReached : ViewAction()
    }

//    data class ViewState(
//        val repos: List<GithubRepo>,
//    )

    val repos = githubRepository.githubRepos

    private var page = INITIAL_PAGE

    init {
        viewModelScope.launch {
            githubRepository.fetchItems(page)
        }
    }

    fun perform(viewAction: ViewAction) {
        when (viewAction) {
            ViewAction.OnEndOfListReached -> loadMore()
        }
    }

    private fun loadMore() {
        page++

        viewModelScope.launch {
            githubRepository.fetchItems(page)
        }
    }

}
