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

    private data class InternalState(
        val nextPage: Int = INITIAL_PAGE,
        val isLoading: Boolean = true,
    )

    private var internalState = InternalState()

    val repos = githubRepository.githubRepos

    init {
        viewModelScope.launch {
            githubRepository.fetchItems(internalState.nextPage)

            internalState = internalState.copy(
                isLoading = false,
            )
        }
    }

    fun perform(viewAction: ViewAction) {
        when (viewAction) {
            ViewAction.OnEndOfListReached -> loadMore()
        }
    }

    private fun loadMore() {
        if (internalState.isLoading) {
            return
        }

        internalState = internalState.copy(
            nextPage = internalState.nextPage + 1,
            isLoading = true,
        )

        viewModelScope.launch {
            githubRepository.fetchItems(internalState.nextPage)

            internalState = internalState.copy(
                isLoading = false,
            )
        }
    }

}
