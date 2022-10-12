package com.shayan.assignment.data.repository

import androidx.room.withTransaction
import com.shayan.assignment.network.api.RemoteDataSource
import com.shayan.assignment.network.dto.GithubRepoDto
import com.shayan.assignment.data.mapper.toEntity
import com.shayan.assignment.data.mapper.toModels
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.model.GithubRepoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
) {

    private val dao = appDatabase.githubRepoDao()

    val githubRepos: Flow<List<GithubRepoModel>> = dao.getAll().map {
        it.toModels()
    }

    suspend fun fetchItems(page: Int) {
        try {
            val response = remoteDataSource.getRepositories(page)

            if (response.isSuccessful && response.body() != null) {
                val repos: List<GithubRepoDto> = response.body()!!
                updateDB(repos, page)
            } else {
                scheduleRetryIfNeeded()
                handleError()
            }
        } catch (e: Exception) {
            handleError()
        }
    }

    private suspend fun updateDB(reposDto: List<GithubRepoDto>, page: Int) {
        if (page == INITIAL_PAGE) {
            appDatabase.withTransaction {
                dao.clearAll()
            }
        }

        val reposEntity = reposDto.mapIndexed { index, repo ->
            repo.toEntity(page, index)
        }

        dao.insertAll(reposEntity)
    }

    private fun handleError() {
        // TODO: set the status to Error
    }

    private fun scheduleRetryIfNeeded() {
        // TODO: schedule a retry worker if it's connectivity error
    }

    companion object {
        const val INITIAL_PAGE = 1
    }
}
