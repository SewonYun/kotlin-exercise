package com.happyint.cyclescape.service.push

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R
import java.util.Calendar

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
            CycleScapeApplication.instance.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    @SuppressLint("ServiceCast", "ScheduleExactAlarm")
    fun scheduleNotification(context: Context, delayMillis: Long) {
        val alarmIntent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context, 0, alarmIntent, PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
//            set(Calendar.HOUR_OF_DAY, 6)
//            set(Calendar.MINUTE, 30)
            set(Calendar.SECOND, 3)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun notifyCycleReminderWithPermissionCheck(getOpenDialog: () -> MutableState<Boolean>) {

        val context = CycleScapeApplication.instance

        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }
            val channel = getNotificationChannel(CHANNEL_ID)

            if (channel!!.importance == NotificationManager.IMPORTANCE_NONE) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }

            val alarmManager: AlarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val hasPermission: Boolean = alarmManager.canScheduleExactAlarms()

            if (!hasPermission) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }

            scheduleNotification(context, 5000)
        }


    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun permissionRequire(getOpenDialog: () -> MutableState<Boolean>) {
        val context = CycleScapeApplication.instance
        with(NotificationManagerCompat.from(context)) {
            if (ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }
            val channel = getNotificationChannel(CHANNEL_ID)

            if (channel!!.importance == NotificationManager.IMPORTANCE_NONE) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }

            val alarmManager: AlarmManager =
                context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val hasPermission: Boolean = alarmManager.canScheduleExactAlarms()

            if (!hasPermission) {
                val openDialog = getOpenDialog()
                openDialog.value = true

                return
            }

        }

    }

    @RequiresApi(Build.VERSION_CODES.S)
    @Composable
    fun ShowPermissionRequest(getOpenDialog: () -> MutableState<Boolean>) {

        val openDialog = getOpenDialog()

        if (!openDialog.value) return

        AlertDialog(onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Permission Required.") },
            text = {

                Column {
                    Card {
                        Text(
                            text = "To receive notifications, please grant the required permissions. \n" + "Tap 'Confirm' to open the app settings and enable the necessary permissions.\n"
                        )
                    }
                }

            },
            confirmButton = {

                Button(onClick = {

                    val alarmManager: AlarmManager =
                        CycleScapeApplication.instance.getSystemService(Context.ALARM_SERVICE) as
                                AlarmManager
                    val hasPermission: Boolean = alarmManager.canScheduleExactAlarms()

                    var intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)

                    if (!hasPermission) {
                        intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                    }

                    openDialog.value = false

                    val uri: Uri = Uri.fromParts(
                        "package", CycleScapeApplication.instance.packageName, null
                    )
                    intent.data = uri

                    val pendingIntent = PendingIntent.getActivity(
                        CycleScapeApplication.instance, 0, intent, PendingIntent.FLAG_IMMUTABLE
                    )

                    try {
                        pendingIntent.send()
                    } catch (e: PendingIntent.CanceledException) {
                        e.printStackTrace()
                    }
                }) {
                    Text(stringResource(id = R.string.confirm))
                }

            })
    }

}
