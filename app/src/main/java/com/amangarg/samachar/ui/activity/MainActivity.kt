package com.amangarg.samachar.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.amangarg.samachar.ui.navigation.SamacharNav
import com.amangarg.samachar.ui.theme.SamacharTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SamacharTheme {
                SamacharNav()
            }
        }
    }
}
