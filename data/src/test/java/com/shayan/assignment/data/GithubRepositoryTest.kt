package com.shayan.assignment.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shayan.assignment.data.mapper.toModels
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.database.dao.GithubRepoDao
import com.shayan.assignment.model.ResultStatus
import com.shayan.assignment.network.api.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class GithubRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val remoteDataSource = mock<RemoteDataSource>()
    private val appDatabase = mock<AppDatabase>()
    private val dao = mock<GithubRepoDao>()

    private lateinit var underTest : GithubRepository

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        `when`(appDatabase.githubRepoDao()).thenReturn(dao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return db items and error when api call fails`() = runBlocking {
        val dbItems = createRepoEntities(40)

        `when`(remoteDataSource.getRepositories(anyInt())).thenReturn(createFailResponse())
        `when`(dao.getAll()).thenReturn(dbItems)

        initGithubRepository()

        val result = underTest.fetchItems(1)
        val expected = dbItems.toModels()

        assertEquals(expected, result.data)
        assertEquals(ResultStatus.ERROR, result.status)
    }

    private fun initGithubRepository() {
        underTest = GithubRepository(
            remoteDataSource = remoteDataSource,
            appDatabase = appDatabase,
            ioContext = Dispatchers.Unconfined
        )
    }

}
