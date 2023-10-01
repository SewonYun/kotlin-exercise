package com.happyint.menstrualcalendar.repositories

import androidx.annotation.WorkerThread
import com.happyint.menstrualcalendar.entities.user.Information

class UserRepository(private val informationDao: InformationDao) {

    fun userInformation(): Information {
        return informationDao.getInformation()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun edit(userInformation: Information) {
        informationDao.editInformation(userInformation)
    }
}