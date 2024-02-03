package com.happyint.cyclescape.service.push

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.SystemClock
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent?.action

        if ("laksdhalskjdi019u312321dlasdkjhasli8hukj" == action) {

            val builder = NotificationCompat.Builder(
                CycleScapeApplication.instance,
                PushNotificationManager.CHANNEL_ID
            )
                .setSmallIcon(R.drawable.rabbit_stamp)
                .setContentTitle("Your cycle is coming soon.")
                .setContentText("Cheering for your ordinary daily life.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

            with(NotificationManagerCompat.from(CycleScapeApplication.instance)) {
                if (ActivityCompat.checkSelfPermission(
                        CycleScapeApplication.instance,
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }

                notify(SystemClock.elapsedRealtime().toInt(), builder.build())
            }

        }

    }
}