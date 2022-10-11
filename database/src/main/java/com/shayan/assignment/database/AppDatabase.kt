package com.shayan.assignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shayan.assignment.database.dao.GithubRepoDao
import com.shayan.assignment.database.entity.GithubRepoEntity

@Database(entities = [GithubRepoEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun githubRepoDao(): GithubRepoDao
}
