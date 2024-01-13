package com.happyint.cyclescape

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

fun getRoomInMemoryAppDatabase(): AppDatabase {
    return Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    ).allowMainThreadQueries().build()
}