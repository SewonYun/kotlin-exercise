@file:OptIn(ExperimentalMaterial3Api::class)

package com.happyint.menstrualcalendar.ui.setting.modal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.happyint.menstrualcalendar.MyApplication
import com.happyint.menstrualcalendar.entities.user.Information
import com.happyint.menstrualcalendar.viewModelFactories.UserInfoViewModelFactory
import com.happyint.menstrualcalendar.viewModels.UserInfoViewModel
import kotlinx.coroutines.launch
import java.time.Year

@ExperimentalMaterial3Api
@Composable

fun BirthDateModal(showIt: MutableState<Boolean>) {

    val skipPartiallyExpanded by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipPartiallyExpanded
    )
    if (showIt.value) {
        ModalBottomSheet(
            onDismissRequest = { showIt.value = false },
            sheetState = bottomSheetState
        ) {

            val factory = UserInfoViewModelFactory(MyApplication.instance.repositories.userRepository)
            val userInfoViewModel: UserInfoViewModel = factory.create(UserInfoViewModel::class.java)

            val userInfo = userInfoViewModel.userInfo.collectAsState().value
            var age by remember { mutableStateOf(userInfo.birth.toString()) }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        scope.launch { bottomSheetState.hide() }.invokeOnCompletion {
                            if (!bottomSheetState.isVisible) {
                                showIt.value = false
                            }
                        }
                    }
                ) {
//                    Text(stringResource(id = R.string.close))
                    Text(age)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
            ) {
                Text(text = age, modifier = Modifier.size(30.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {

                val year = Year.now()
                val ageStartYear = year.value - 70
                val ageLastYear = year.value - 10
                items(ageLastYear - ageStartYear) {it
                    val ageInModal = (ageLastYear - it).toString()
                    ListItem(
                        modifier = Modifier.clickable {
                            userInfoViewModel.updateUserInfo(
                                userInformation = Information(id = 0, birth = ageInModal, name = userInfo.name)
                            )
                        },
                        headlineContent = {
                            Text("$ageInModal")
                        },
                        leadingContent = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }

            }

        }
    }
}