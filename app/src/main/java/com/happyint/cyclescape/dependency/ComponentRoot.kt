package com.happyint.cyclescape.dependency

import android.app.Application
import androidx.compose.foundation.ExperimentalFoundationApi
import com.happyint.cyclescape.dependency.module.RepositoryModule
import com.happyint.cyclescape.viewModels.CalendarViewModel
import com.happyint.cyclescape.viewModels.ComposableCalendarViewModel
import com.happyint.cyclescape.viewModels.LittleNoteViewModel
import com.happyint.cyclescape.viewModels.UserInfoViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface ComponentRoot {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ComponentRoot
    }

    fun inject(userInfoViewModel: UserInfoViewModel)

    fun inject(calendarViewModel: CalendarViewModel)

    fun inject(littleNoteViewModel: LittleNoteViewModel)

    @OptIn(ExperimentalFoundationApi::class)
    fun inject(composableCalendarViewModel: ComposableCalendarViewModel)

}