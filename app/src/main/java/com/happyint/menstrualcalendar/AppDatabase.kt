package com.happyint.menstrualcalendar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.happyint.menstrualcalendar.entities.calendar.data.DayData
import com.happyint.menstrualcalendar.entities.user.data.Information
import com.happyint.menstrualcalendar.repositories.DayDataDao
import com.happyint.menstrualcalendar.repositories.InformationDao
import com.happyint.menstrualcalendar.util.LocalDateConverter

@Database(
    version = 3,
    entities = [Information::class, DayData::class]
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): InformationDao
    abstract fun dayDataDao(): DayDataDao

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
