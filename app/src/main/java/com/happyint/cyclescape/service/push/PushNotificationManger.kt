package com.happyint.cyclescape.service.push

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R

object PushNotificationManager {

    const val CHANNEL_ID = "CYCLE_REMIND_PUSH"

    fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        val name = "Cycle reminder"
        val descriptionText = "alarm saying user cycle coming soon"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            CycleScapeApplication.instance.getSystemService(Context.NOTIFICATION_SERVICE) as
                    NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun notifyCycleReminder() {

        val builder = NotificationCompat.Builder(CycleScapeApplication.instance, CHANNEL_ID)
            .setSmallIcon(R.drawable.rabbit_stamp)
            .setContentTitle("asdasd")
            .setContentText("asdasdasd222 content text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)


        with(NotificationManagerCompat.from(CycleScapeApplication.instance)) {
            // notificationId is a unique int for each notification that you must define
            if (ActivityCompat.checkSelfPermission(
                    CycleScapeApplication.instance,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                Toast.makeText(CycleScapeApplication.instance, "permission plz", Toast.LENGTH_SHORT)
                    .show()
                return@with
            }
            notify(1232139, builder.build())
        }
    }
}
