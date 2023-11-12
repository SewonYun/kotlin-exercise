package com.happyint.cyclescape

import com.happyint.cyclescape.repositories.DayDataRepository
import com.happyint.cyclescape.repositories.UserRepository

class RepositoryAdapt(database: AppDatabase) {

    val userRepository: UserRepository by lazy { UserRepository(database.userDao()) }
    val dayDataRepository: DayDataRepository by lazy { DayDataRepository(database.dayDataDao()) }

}
