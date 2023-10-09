package com.happyint.menstrualcalendar.ui.setting.modal

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.MyApplication
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.constants.Numbers
import com.happyint.menstrualcalendar.entities.user.data.Information
import com.happyint.menstrualcalendar.util.ViewModelProvider


@ExperimentalMaterial3Api
@Composable
fun NameDialog(disappearCallback: () -> Unit) {
    val userInfoViewModel = ViewModelProvider.getUserInfoViewModel()
    var localScopeName: String by remember { mutableStateOf(userInfoViewModel.name.value) }

    AlertDialog(
        onDismissRequest = {
            Toast.makeText(
                MyApplication.instance,
                MyApplication.instance.getString(R.string.did_not_save),
                Toast.LENGTH_SHORT
            ).show()

            disappearCallback()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation
        ) {
            var isError = remember { mutableStateOf(false) }
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = localScopeName,
                    onValueChange = {

                        localScopeName = it.take(Numbers.MAX_NICKNAME_LENGTH.value)
                        isError.value = it.length > Numbers.MAX_NICKNAME_LENGTH.value

                        if (isError.value) {
                            Toast.makeText(
                                MyApplication.instance,
                                MyApplication.instance.getString(R.string.nick_max_length_alert),
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                    isError = isError.value,
                    singleLine = true,
                    label = { Text(text = "Nick name") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = {
                        userInfoViewModel.updateUserInfo(
                            Information(
                                id = 0,
                                name = localScopeName,
                                userInfoViewModel.birth.value,
                                averageMenstrualCycle = 0
                            )
                        )
                        disappearCallback()
                    },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Change")
                }
            }
        }

    }
}