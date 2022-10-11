package com.shayan.assignment.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shayan.assignment.data.GithubRepoRemoteMediator
import com.shayan.assignment.database.dao.GithubRepoDao
import com.shayan.assignment.database.entity.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

class GithubRepository(
    private val githubRepoRemoteMediator: GithubRepoRemoteMediator,
    private val githubRepoDao: GithubRepoDao,
//    private val ioContext: CoroutineContext = Dispatchers.IO,
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getGithubRepos() : Flow<PagingData<GithubRepoEntity>> =
        Pager(
            config = PagingConfig(PAGE_SIZE),
            remoteMediator = githubRepoRemoteMediator,
        ) {
             githubRepoDao.pagingSource()
        }.flow

    companion object {
        const val PAGE_SIZE = 20
    }
}

