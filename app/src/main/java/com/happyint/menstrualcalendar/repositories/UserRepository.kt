package com.happyint.menstrualcalendar.repositories

import com.happyint.menstrualcalendar.entities.user.Information

class UserRepository(private val informationDao: InformationDao) {

    val userInformation : Information = informationDao.getInformation()
}