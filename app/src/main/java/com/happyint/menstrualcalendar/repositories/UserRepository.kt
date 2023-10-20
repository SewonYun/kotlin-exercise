package com.happyint.menstrualcalendar.repositories

import com.happyint.menstrualcalendar.entities.user.data.Information

class UserRepository(private val informationDao: InformationDao) {

    fun userInformation(): Information {
        return informationDao.getInformation()
    }

    fun edit(userInformation: Information) {
        informationDao.insertInformation(userInformation)
    }
}