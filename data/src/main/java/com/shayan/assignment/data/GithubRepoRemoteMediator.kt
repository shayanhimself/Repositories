package com.shayan.assignment.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.shayan.assignment.data.api.GithubService
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.database.entity.GithubRepoEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GithubRepoRemoteMediator(
    private val githubService: GithubService,
    private val appDatabase: AppDatabase,
) : RemoteMediator<Int, GithubRepoEntity>() {

    private val githubRepoDao = appDatabase.githubRepoDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GithubRepoEntity>
    ): MediatorResult {
        return try {
            val loadKey: Int? = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }

                    lastItem.id
                }
            }

            //TODO: page & size
            val response = githubService.getRepositories(DataConstants.DEFAULT_USER_NAME, 1, 10)
                .map { it.toEntity() }

            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    githubRepoDao.clearAll()
                }

                githubRepoDao.insertAll(response)
            }

            // TODO: check response.nextKey
            return MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
