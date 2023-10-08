@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.happyint.menstrualcalendar.MyApplication
import com.happyint.menstrualcalendar.R
import com.happyint.menstrualcalendar.customApi.testBorder
import com.happyint.menstrualcalendar.ui.setting.modal.AverageCycleBottomSheet
import com.happyint.menstrualcalendar.ui.setting.modal.BirthDateBottomSheet
import com.happyint.menstrualcalendar.ui.setting.modal.NameDialog
import com.happyint.menstrualcalendar.util.ViewModelProvider
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel

@Composable
fun nameField(userInfoViewModel: UserInfoViewModel): String {
    return if (userInfoViewModel.name.collectAsState().value == "") {
        MyApplication.instance.getString(R.string.default_user_nickname)
    } else {
        userInfoViewModel.name.collectAsState().value
    }
}

@ExperimentalMaterial3Api
@Composable
fun UserInfo() {
    val showBirthModal = remember { mutableStateOf(false) }
    BirthDateBottomSheet(showBirthModal)

    val showAverageCycleBottomSheet = remember { mutableStateOf(false) }
    AverageCycleBottomSheet(showAverageCycleBottomSheet)

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
                .testBorder()
        ) {

            var showDialog by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier
                    .clickable {

                    }
                    .width(200.dp)
                    .padding(10.dp)
                    .testBorder(),

                ) {

                if (showDialog) {
                    NameDialog {
                        showDialog = false
                    }
                }

                Text(
                    modifier = Modifier
                        .testBorder()
                        .fillMaxWidth()
                        .height(35.dp)
                        .clickable { showDialog = true },
                    text = nameField(userInfoViewModel)
                )

            }

            Column (
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight()
                    .padding(10.dp)
                    .testBorder()
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .testBorder()
                    .clickable { showBirthModal.value = true }
                ) {

                    val txt = if (obAge.isNullOrEmpty()) "" else
                        stringResource(id = R.string.age_expression, obAge.toInt())
                    Text(
                        text = txt, style =
                        TextStyle(fontSize = 20.sp)
                    )
                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        LineBox(
            stringResource(R.string.avg_cycle),
            ImageVectorContainer(Icons.Default.AddCircle),
            borderTop = true,
            borderBottom = true
        ) {
            showAverageCycleBottomSheet.value = true
        }

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
