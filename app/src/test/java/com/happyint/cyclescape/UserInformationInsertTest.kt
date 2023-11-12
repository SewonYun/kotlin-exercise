package com.happyint.cyclescape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.happyint.cyclescape.entities.user.data.Information
import com.happyint.cyclescape.repositories.InformationDao
import com.happyint.cyclescape.repositories.UserRepository
import com.happyint.cyclescape.viewModelFactories.UserInfoViewModelFactory
import com.happyint.cyclescape.viewModels.UserInfoViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@ExperimentalMaterial3Api
@RunWith(RobolectricTestRunner::class)
class UserInformationInsertTest {

    private lateinit var mockDatabase: AppDatabase
    private lateinit var mockInformationDao: InformationDao
    private lateinit var userInfoViewModel: UserInfoViewModel

    @Before
    fun setup() {
        mockDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        mockInformationDao = mockDatabase.userDao()

        userInfoViewModel =
            UserInfoViewModelFactory(UserRepository(mockInformationDao)).create(UserInfoViewModel::class.java)
    }

    @Test
    fun testUserInformationInsertWithGet(): Unit = runBlocking {

        val testData = Information(0, "test", "1984-04-01", 12)
        userInfoViewModel.updateUserInfo(testData).join()
        val getDayData: Information = userInfoViewModel.information.value

        assert(getDayData != null, lazyMessage = { "db insert failed" })

        if (getDayData != null) {
            assert((testData.id < getDayData.id), lazyMessage = { "invalid id" })
            assert((testData.name == getDayData.name), lazyMessage = { "not equal name" })
            assert((testData.birth == getDayData.birth), lazyMessage = { "not equal birth" })
            assert((testData.averageMenstrualCycle == getDayData.averageMenstrualCycle),
                lazyMessage = { "not equal average cycle" })
        }

    }

    @After
    fun tearDown() {
        mockDatabase.close()
    }
}