package com.happyint.menstrualcalendar

import com.happyint.menstrualcalendar.repositories.DayDataRepository
import com.happyint.menstrualcalendar.repositories.UserRepository

class RepositoryAdapt(database: AppDatabase) {

    val userRepository: UserRepository by lazy { UserRepository(database.userDao()) }
    val dayDataRepository: DayDataRepository by lazy { DayDataRepository(database.dayDataDao()) }

}
