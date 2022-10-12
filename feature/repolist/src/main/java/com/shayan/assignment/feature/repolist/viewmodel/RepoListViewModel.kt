package com.shayan.assignment.feature.repolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.data.repository.GithubRepository.Companion.INITIAL_PAGE
import com.shayan.assignment.model.GithubRepoModel
import com.shayan.assignment.model.Result
import com.shayan.assignment.model.ResultStatus
import kotlinx.coroutines.launch

class RepoListViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    sealed class ViewAction {
        object OnSwipeToRefresh : ViewAction()
        object OnEndOfListReached : ViewAction()
    }

    private data class InternalState(
        val nextPage: Int = INITIAL_PAGE,
        val isLoading: Boolean = true,
        val hasMoreItems: Boolean = true,
    )

    private var internalState = InternalState()

    private val _reposLiveData = MutableLiveData<List<GithubRepoModel>>()
    val reposLiveData: LiveData<List<GithubRepoModel>> = _reposLiveData

    init {
        fetchItems()
    }

    fun perform(viewAction: ViewAction) {
        when (viewAction) {
            ViewAction.OnEndOfListReached -> loadMore()
            ViewAction.OnSwipeToRefresh -> refresh()
        }
    }

    private fun loadMore() {
        if (internalState.isLoading || !internalState.hasMoreItems) {
            return
        }

        fetchItems()
    }

    private fun refresh() {
        internalState = InternalState(
            nextPage = INITIAL_PAGE
        )

        fetchItems()
    }

    private fun fetchItems() {
        internalState = internalState.copy(
            isLoading = true,
        )

        viewModelScope.launch {
            val result = githubRepository.fetchItems(internalState.nextPage)

            when (result.status) {
                ResultStatus.SUCCESS -> {
                    handleSuccessResult(result)
                }
                ResultStatus.ERROR -> {
                    handleFailedResult(result)
                }
            }

        }
    }

    private fun handleSuccessResult(result: Result<List<GithubRepoModel>>) {
        internalState = internalState.copy(
            nextPage = internalState.nextPage + 1,
            isLoading = false,
            hasMoreItems = !result.isLastPage,
        )
        _reposLiveData.value = result.data
    }

    private fun handleFailedResult(result: Result<List<GithubRepoModel>>) {
        internalState = internalState.copy(
            isLoading = false,
        )
        _reposLiveData.value = result.data
    }

}
