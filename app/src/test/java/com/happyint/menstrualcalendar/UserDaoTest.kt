package com.happyint.menstrualcalendar

import androidx.room.Room
import com.happyint.menstrualcalendar.entities.user.data.Information
import com.happyint.menstrualcalendar.repositories.InformationDao
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UserDaoTest {
    private lateinit var informationDao: InformationDao
    private lateinit var db: AppDatabase

    // todo: Some lateinit var is not initiated
    @Before
    fun createDB() {
        db = Room.inMemoryDatabaseBuilder(PeriodApplication.instance, AppDatabase::class.java)
            .build()
        informationDao = db.userDao()
    }

    @After
    fun closeDB() {
        db.close()
    }

    @Test
    fun testInsertAndGetUser() = runBlocking {
        val user = Information(9999, "tester", "2002-06-15", 0)
        informationDao.insertInformation(user)
        val userFromDb = informationDao.getInformationById(user.id)

        assertEquals(user.id, userFromDb.id)
        assertEquals(user.name, userFromDb.name)
    }
}