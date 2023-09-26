package com.happyint.menstrualcalendar

import com.happyint.menstrualcalendar.entities.user.Information
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Bootstrap {

    fun on() {

        CoroutineScope(Dispatchers.IO).launch {
            val db = MyApplication.instance.database

            (db.userDao()).getInformation() ?: {
                val informationStub = Information(id = 0, name = "un_name", birth = "1000-00-00")
                (db.userDao()).editInformation(informationStub)
            }
        }

    }
}