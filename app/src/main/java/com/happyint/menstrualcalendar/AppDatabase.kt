package com.happyint.menstrualcalendar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.repositories.InformationDao

@Database(
    version = 2,
    entities = [Information::class]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): InformationDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                return Room.databaseBuilder(
                    context, AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }

}
