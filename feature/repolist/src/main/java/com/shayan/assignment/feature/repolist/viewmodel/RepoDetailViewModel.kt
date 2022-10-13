package com.shayan.assignment.feature.repolist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.model.GithubRepoModel
import kotlinx.coroutines.launch

class RepoDetailViewModel(
    private val githubRepository: GithubRepository,
    private val repoId: Int,
) : ViewModel() {

    private val _repoLiveData = MutableLiveData<GithubRepoModel>()
    val repoLiveData: LiveData<GithubRepoModel> = _repoLiveData

    init {
        viewModelScope.launch {
            _repoLiveData.value= githubRepository.getItem(repoId)
        }
    }

}
