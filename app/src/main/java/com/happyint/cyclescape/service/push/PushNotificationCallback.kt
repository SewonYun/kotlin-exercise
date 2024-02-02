package com.happyint.cyclescape.service.push

import com.happyint.cyclescape.entities.push.PushNotification

interface PushNotificationCallback {
    fun onNotificationReceived(notification: PushNotification)
    fun onNotificationClicked(notification: PushNotification)
}
