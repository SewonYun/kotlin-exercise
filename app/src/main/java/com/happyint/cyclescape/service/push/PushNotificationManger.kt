package com.happyint.cyclescape.service.push

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R

object PushNotificationManager {

    private const val CHANNEL_ID = "CYCLE_REMIND_PUSH"

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

    fun notifyCycleReminder(getOpenDialog: () -> MutableState<Boolean>) {

        val builder = NotificationCompat.Builder(CycleScapeApplication.instance, CHANNEL_ID)
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

            notify(1232139, builder.build())
        }

    }

    fun permissionRequire(getOpenDialog: () -> MutableState<Boolean>) {

        with(NotificationManagerCompat.from(CycleScapeApplication.instance)) {
            // notificationId is a unique int for each notification that you must define
            val channel = getNotificationChannel(CHANNEL_ID)

            if (channel!!.importance == NotificationManager.IMPORTANCE_NONE) {

                val openDialog = getOpenDialog()
                openDialog.value = true

                return@with
            }

        }

    }

    @Composable
    fun ShowPermissionRequest(getOpenDialog: () -> MutableState<Boolean>) {

        val openDialog = getOpenDialog()

        if (!openDialog.value) return

        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Permission Required.") },
            text = {

                Column {
                    Card {
                        Text(
                            text = "To receive notifications, please grant the required permissions. \n" +
                                    "Tap 'Confirm' to open the app settings and enable the necessary permissions.\n"
                        )
                    }
                }

            },
            confirmButton = {

                Button(onClick = {

                    openDialog.value = false
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri: Uri = Uri.fromParts(
                        "package", CycleScapeApplication.instance.packageName,
                        null
                    )
                    intent.data = uri

                    val pendingIntent = PendingIntent.getActivity(
                        CycleScapeApplication.instance,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                    )

                    try {
                        pendingIntent.send()
                    } catch (e: PendingIntent.CanceledException) {
                        e.printStackTrace()
                    }
                }) {
                    Text(stringResource(id = R.string.confirm))
                }

            }
        )
    }

}
