package com.shayan.assignment.database.di

import androidx.room.Room
import com.shayan.assignment.database.AppDatabase
import com.shayan.assignment.database.DatabaseConstants.DATABASE_NAME
import com.shayan.assignment.database.dao.GithubRepoDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseKoinModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME,
        ).build()
    }

    factory<GithubRepoDao> {
        get<AppDatabase>().githubRepoDao()
    }
}
