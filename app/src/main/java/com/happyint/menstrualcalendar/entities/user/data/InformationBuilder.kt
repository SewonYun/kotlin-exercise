package com.happyint.menstrualcalendar.entities.user.data

class InformationBuilder {

    companion object {
        fun getEmptyInformation(): Information {
            return Information(id = 0, name = "", birth = "", averageMenstrualCycle = 0)
        }
    }
}