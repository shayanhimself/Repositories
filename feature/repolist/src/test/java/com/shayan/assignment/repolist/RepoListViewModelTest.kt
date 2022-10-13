package com.shayan.assignment.repolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shayan.assignment.data.repository.GithubRepository
import com.shayan.assignment.feature.repolist.mapper.ErrorMapper
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel
import com.shayan.assignment.feature.repolist.viewmodel.RepoListViewModel.ViewAction.OnEndOfListReached
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.After
import org.junit.Test
import org.junit.Rule
import org.junit.Assert.assertEquals
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val githubRepository = mock<GithubRepository>()
    private val errorMapper = mock<ErrorMapper>()

    private lateinit var underTest: RepoListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should fetch 1 page when initialised`() = runBlocking {
        val repos = createRepoModels(count = 10)
        `when`(githubRepository.fetchItems(anyInt())).thenReturn(
            createSuccessResults(repos)
        )

        initViewModel()

        assertEquals(repos, underTest.reposLiveData.value)
    }

    @Test
    fun `should fetch 4 pages and stop when it's last page`() = runBlocking {
        val repos = createRepoModels(count = 35)

        `when`(githubRepository.fetchItems(1)).thenReturn(
            createSuccessResults(repos = repos.subList(0, 10))
        )
        `when`(githubRepository.fetchItems(2)).thenReturn(
            createSuccessResults(repos = repos.subList(0, 20))
        )
        `when`(githubRepository.fetchItems(3)).thenReturn(
            createSuccessResults(repos = repos.subList(0, 30))
        )
        `when`(githubRepository.fetchItems(4)).thenReturn(
            createSuccessResults(
                repos = repos,
                isLastPage = true
            )
        )

        initViewModel()

        repeat((0..10).count()) {
            underTest.perform(OnEndOfListReached)
        }

        verify(githubRepository, times(4)).fetchItems(anyInt())

        assertEquals(repos, underTest.reposLiveData.value)
    }

    private fun initViewModel() {
        underTest = RepoListViewModel(
            githubRepository,
            errorMapper,
        )
    }
}
