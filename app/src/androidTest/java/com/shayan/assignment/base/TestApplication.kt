package com.shayan.assignment.base

import android.app.Application
import android.content.Context
import com.shayan.assignment.data.di.dataKoinModule
import com.shayan.assignment.database.di.databaseKoinModule
import com.shayan.assignment.feature.repolist.di.repoListKoinModule
import com.shayan.assignment.network.api.GithubService
import com.shayan.assignment.network.di.networkKoinModule
import com.shayan.assignment.mocked.GithubServiceMock
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class TestApplication : Application() {

    private val testModule = module {
        single<GithubService>(override = true) { GithubServiceMock() }
    }

    override fun attachBaseContext(base: Context?) {
        startKoin {
            androidContext(this@TestApplication)
            modules(listOf(
                databaseKoinModule,
                dataKoinModule,
                networkKoinModule,
                repoListKoinModule,
                testModule,
            ))
        }
        super.attachBaseContext(base)
    }
}
