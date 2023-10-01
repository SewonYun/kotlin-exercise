package com.happyint.menstrualcalendar

import com.happyint.menstrualcalendar.repositories.UserRepository

class RepositoryAdapt(database: AppDatabase) {

    val userRepository: UserRepository by lazy { UserRepository(database.userDao()) }

}
