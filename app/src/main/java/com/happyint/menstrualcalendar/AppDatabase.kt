package com.happyint.menstrualcalendar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.repositories.InformationDao

@Database(entities = [Information::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): InformationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}
