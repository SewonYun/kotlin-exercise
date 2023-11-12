package com.happyint.cyclescape.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.happyint.cyclescape.entities.user.data.Information

@Dao
interface InformationDao {
    @Query("SELECT * FROM information")
    fun select(): Information?

    @Query("SELECT * FROM information ORDER BY id DESC LIMIT 0, 1")
    fun getInformation(): Information

    @Query("SELECT * FROM information WHERE id = :id")
    fun getInformationById(id: Int): Information

    @Insert
    fun insertInformation(information: Information): Long
}