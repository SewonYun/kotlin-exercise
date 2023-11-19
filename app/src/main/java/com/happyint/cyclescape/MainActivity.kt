package com.happyint.cyclescape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.happyint.cyclescape.ui.graphics.AppTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            (Bootstrap()).on()
            AppTheme {
                CycleScapeAppOf()
            }
        }
    }
}