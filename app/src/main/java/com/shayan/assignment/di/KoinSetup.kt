package com.shayan.assignment.di

import android.app.Application
import com.shayan.assignment.data.di.dataKoinModule
import com.shayan.assignment.data.di.networkKoinModule
import com.shayan.assignment.database.di.databaseKoinModule
import com.shayan.assignment.feature.repolist.di.repoListKoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun initKoin(application: Application) {
    startKoin {
        androidContext(application)
        modules(listOf(
            databaseKoinModule,
            dataKoinModule,
            networkKoinModule,
            repoListKoinModule,
        ))
    }
}
