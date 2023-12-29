package com.happyint.cyclescape.ui.setting.modal

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.happyint.cyclescape.CycleScapeApplication
import com.happyint.cyclescape.R
import com.happyint.cyclescape.constants.Numbers
import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.viewModels.UserInfoViewModel


@ExperimentalMaterial3Api
@Composable
fun NameDialog(disappearCallback: () -> Unit) {
    val userInfoViewModel = viewModel<UserInfoViewModel>()
    val localScopeName = userInfoViewModel.information.collectAsState()
        .value
        .name!!

    var rememberdName: String by remember { mutableStateOf(localScopeName) }

    AlertDialog(
        onDismissRequest = {
            Toast.makeText(
                CycleScapeApplication.instance,
                CycleScapeApplication.instance.getString(R.string.did_not_save),
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
            val isError = remember { mutableStateOf(false) }
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = rememberdName,
                    onValueChange = {

                        rememberdName = it.take(Numbers.MAX_NICKNAME_LENGTH.value)
                        isError.value = it.length > Numbers.MAX_NICKNAME_LENGTH.value

                        if (isError.value) {
                            Toast.makeText(
                                CycleScapeApplication.instance,
                                CycleScapeApplication.instance.getString(
                                    R.string
                                        .nick_max_length_alert, Numbers.MAX_NICKNAME_LENGTH.value
                                ),
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
                                name = rememberdName,
                                birth = userInfoViewModel.information.value.birth,
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