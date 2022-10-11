package com.shayan.assignment.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shayan.assignment.database.entity.GithubRepoEntity

@Dao
interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GithubRepoEntity>)

    @Query("SELECT * FROM repos")
    fun pagingSource(): PagingSource<Int, GithubRepoEntity>

    @Query("DELETE FROM repos")
    suspend fun clearAll()
}
