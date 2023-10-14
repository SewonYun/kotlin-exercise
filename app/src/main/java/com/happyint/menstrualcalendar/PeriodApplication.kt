package com.happyint.menstrualcalendar

import android.app.Application
import android.content.Context

class PeriodApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    val repositories: RepositoryAdapt by lazy { RepositoryAdapt(database) }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: PeriodApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}