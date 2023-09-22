package com.happyint.menstrualcalendar

import android.app.Application
import android.content.Context

class MyApplication: Application() {

    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MyApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}