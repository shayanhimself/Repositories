package com.shayan.assignment.data.repository

import androidx.room.withTransaction
import com.shayan.assignment.network.api.RemoteDataSource
import com.shayan.assignment.network.dto.GithubRepoDto
import com.shayan.assignment.data.mapper.toEntity
import com.shayan.assignment.data.mapper.toModel
import com.shayan.assignment.data.mapper.toModels
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.model.ErrorType
import com.shayan.assignment.model.GithubRepoModel
import com.shayan.assignment.model.Result
import com.shayan.assignment.model.ResultStatus
import com.shayan.assignment.network.ApiConstants.INITIAL_PAGE
import com.shayan.assignment.network.utils.isLastPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import kotlin.coroutines.CoroutineContext

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val appDatabase: AppDatabase,
    private val ioContext: CoroutineContext = Dispatchers.IO,
) {

    private val dao = appDatabase.githubRepoDao()

    suspend fun fetchItems(page: Int): Result<List<GithubRepoModel>> = try {
        val response = remoteDataSource.getRepositories(page)

        if (response.isSuccessful && response.body() != null) {
            val repos: List<GithubRepoDto> = response.body()!!
            updateDB(repos, page)
            createSuccessResults(response)
        } else {
            createErrorResults()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        createErrorResults(e)
    }

    suspend fun getItem(repoId: Int): GithubRepoModel = withContext(ioContext) {
        dao.get(repoId).toModel()
    }

    private suspend fun createSuccessResults(
        response: Response<List<GithubRepoDto>>
    ) = withContext(ioContext) {
        Result(
            data = dao.getAll().toModels(),
            status = ResultStatus.SUCCESS,
            isLastPage = response.isLastPage(),
        )
    }

    private suspend fun createErrorResults(ioException: IOException? = null) = withContext(ioContext) {
        Result(
            data = dao.getAll().toModels(),
            status = ResultStatus.ERROR,
            errorType = getErrorType(ioException),
            errorMessage = ioException?.message,
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

        appDatabase.withTransaction {
            dao.insertAll(reposEntity)
        }
    }

    private fun getErrorType(ioException: IOException?): ErrorType = if (ioException != null) {
        ErrorType.CONNECTIVITY
    } else {
        ErrorType.GENERAL
    }

}
