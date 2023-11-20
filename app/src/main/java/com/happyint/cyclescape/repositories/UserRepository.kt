package com.happyint.cyclescape.repositories

import com.happyint.cyclescape.entities.user.data.Information
import javax.inject.Inject

class UserRepository @Inject constructor(private val informationDao: InformationDao) {

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