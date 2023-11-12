package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.user.data.Information

class UserRepository(private val informationDao: InformationDao) {

    fun userInformation(): Information {
        return informationDao.getInformation()
    }

    fun insert(userInformation: Information) {
        informationDao.insertInformation(userInformation)
    }

    fun getByUserId(id: Int): Information {
        return informationDao.getInformationById(id)
    }
}