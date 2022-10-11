package com.shayan.assignment

import android.app.Application
import android.content.Context
import com.shayan.assignment.di.initKoin

class AssignmentApplication: Application() {
    override fun attachBaseContext(base: Context?) {
        initKoin(this)
        super.attachBaseContext(base)
    }
}
