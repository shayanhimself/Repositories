package com.shayan.assignment.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shayan.assignment.database.entity.GithubRepoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<GithubRepoEntity>)

    @Query("SELECT * FROM repos ORDER BY orderIndex ASC")
    suspend fun getAll(): List<GithubRepoEntity>

    @Query("DELETE FROM repos")
    suspend fun clearAll()
}
