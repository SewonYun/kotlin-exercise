package com.happyint.cyclescape

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.happyint.cyclescape.entities.calendar.data.DayData
import com.happyint.cyclescape.entities.littleNote.data.DailyNoteData
import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.repositories.DayDataDao
import com.happyint.cyclescape.repositories.InformationDao
import com.happyint.cyclescape.repositories.LittleNoteDao
import com.happyint.cyclescape.util.LocalDateConverter

@Database(
    version = 11,
    entities = [Information::class, DayData::class, DailyNoteData::class],
    exportSchema = false
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): InformationDao
    abstract fun dayDataDao(): DayDataDao
    abstract fun littleNoteDao(): LittleNoteDao

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
