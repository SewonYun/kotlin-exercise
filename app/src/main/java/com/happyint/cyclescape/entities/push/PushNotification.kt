package com.happyint.cyclescape.entities.push

data class PushNotification(
    val title: String,
    val message: String,
    val data: Map<String, String>
)

