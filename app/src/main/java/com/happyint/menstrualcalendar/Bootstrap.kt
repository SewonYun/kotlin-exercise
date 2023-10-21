package com.happyint.menstrualcalendar

import com.happyint.menstrualcalendar.entities.user.data.Information
import com.happyint.menstrualcalendar.entities.user.data.InformationBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Bootstrap {

    fun on() {

        CoroutineScope(Dispatchers.IO).launch {
            val db = PeriodApplication.instance.database
            val informationOption: Information? = (db.userDao()).select()
            if (informationOption == null) {
                (db.userDao()).insertInformation(InformationBuilder.getEmptyInformation())
            }
        }

    }
}