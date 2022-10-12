package com.shayan.assignment.data.repository

import androidx.room.withTransaction
import com.shayan.assignment.network.api.RemoteDataSource
import com.shayan.assignment.network.dto.GithubRepoDto
import com.shayan.assignment.data.mapper.toEntity
import com.shayan.assignment.data.mapper.toModels
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.model.ErrorType
import com.shayan.assignment.model.GithubRepoModel
import com.shayan.assignment.model.Result
import com.shayan.assignment.model.ResultStatus
import com.shayan.assignment.network.utils.isLastPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
    private val ioContext: CoroutineContext = Dispatchers.IO,
) {

    private val dao = appDatabase.githubRepoDao()

    suspend fun fetchItems(page: Int): Result<List<GithubRepoModel>> {
        try {
            val response = remoteDataSource.getRepositories(page)

            if (response.isSuccessful && response.body() != null) {
                val repos: List<GithubRepoDto> = response.body()!!
                updateDB(repos, page)
                return createSuccessResults(response)
            } else {
                handleError()
                return Result(
                    data = dao.getAll().toModels(),
                    status = ResultStatus.ERROR,
                    false,
                    errorType = ErrorType.CONNECTIVITY,
                    errorMessage = "fake error",
                )
            }
        } catch (e: Exception) {
            handleError()
            return Result(
                data = dao.getAll().toModels(),
                status = ResultStatus.ERROR,
                false,
                errorType = ErrorType.CONNECTIVITY,
                errorMessage = "fake error",
            )
        }
    }

    private suspend fun createSuccessResults(
        response: Response<List<GithubRepoDto>>
    ) = withContext(ioContext) {
        Result(
            data = dao.getAll().toModels(),
            status = ResultStatus.SUCCESS,
            isLastPage = response.isLastPage()
        )
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

    companion object {
        const val INITIAL_PAGE = 1
    }
}

