package com.happyint.cyclescape

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CycleScapeApplication : Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    init {
        instance = this
    }

    companion object {
        lateinit var instance: CycleScapeApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}