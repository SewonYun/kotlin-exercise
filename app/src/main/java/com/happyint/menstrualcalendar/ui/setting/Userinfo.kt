@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.ui.setting.modal.BirthDateModal
import com.happyint.menstrualcalendar.util.ViewModelProvider
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

@Composable
fun nameField(userInfoViewModel: UserInfoViewModel): String {
    return if (userInfoViewModel.name.collectAsState().value == "") {
        stringResource(id = R.string.default_user_nickname)
    } else {
        userInfoViewModel.name.collectAsState().value
    }
}

@ExperimentalMaterial3Api
@Composable
fun UserInfo() {
    val showBirthModal = remember { mutableStateOf(false) }
    BirthDateModal(showBirthModal)

    val userInfoViewModel: UserInfoViewModel = ViewModelProvider.getUserInfoViewModel()
    val obAge by userInfoViewModel.birth.collectAsState()

    Column(
        modifier = Modifier.padding(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {

            Column(
                modifier = Modifier
                    .width(200.dp)
                    .padding(10.dp),

                ) {
                Text(text = nameField(userInfoViewModel), style = TextStyle(fontSize = 20.sp))
            }

            Column (
                modifier = Modifier
                    .width(200.dp)
                    .padding(10.dp)
            ) {
                Text(text = "$obAge 년생", style = TextStyle(fontSize = 20.sp))
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.birth_date),
            ImageVectorContainer(
                if (obAge.isNullOrEmpty()) {
                    Icons.Default.AddCircle
                } else {
                    Icons.Default.Done
                }
            ),
            borderTop = true,
            borderBottom = true
        ) { showBirthModal.value = true }

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.avg_cycle),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.reminder),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.backup),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(80.dp))

        Divider(
            color = Color.LightGray, modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Text(stringResource(R.string.help), style = TextStyle(fontSize = 16.sp))
        }

        LineBox(
            "FAQ",
            ImageVectorContainer(),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.contact_us),
            ImageVectorContainer(),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            "notice",
            ImageVectorContainer(),
            borderTop = true,
            borderBottom = true
        )

        Spacer(modifier = Modifier.height(75.dp))
    }
}
