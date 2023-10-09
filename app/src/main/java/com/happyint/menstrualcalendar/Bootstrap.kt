package com.happyint.menstrualcalendar

import com.happyint.menstrualcalendar.entities.user.data.Information
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Bootstrap {

    fun on() {

        CoroutineScope(Dispatchers.IO).launch {
            val db = MyApplication.instance.database
            val informationOption: Information? = (db.userDao()).select()
            if (informationOption == null) {
                val informationStub = Information(
                    id = 0, name = "", birth = "",
                    averageMenstrualCycle = 0
                )
                (db.userDao()).editInformation(informationStub)
            }
        }

    }
}