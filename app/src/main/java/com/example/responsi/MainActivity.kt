package com.example.responsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.responsi.BMRCalculatorScreen
import com.example.responsi.RegisterScreen
import com.example.responsi.UserDataScreen
import com.example.responsi.ui.theme.ResponsiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiTheme {
                val navController = rememberNavController() // Menyimpan kontrol navigasi
                NavHost(navController = navController, startDestination = "register") {
                    composable("register") { RegisterScreen(navController) }
                    composable("bmr_calculator") { BMRCalculatorScreen(navController) }
                    composable("user_data") { UserDataScreen() }
                }
            }
        }
    }
}
