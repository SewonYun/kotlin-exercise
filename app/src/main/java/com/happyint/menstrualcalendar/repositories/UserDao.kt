package com.happyint.menstrualcalendar.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.happyint.menstrualcalendar.entities.user.Information

@Dao
interface UserDao {
@Query("SELECT * FROM information ORDER BY id DESC LIMIT 0, 1")
    fun getInformation(): Information

    @Insert
    fun editInformation(information: Information)
}