package com.example.responsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.*
import com.example.responsi.ui.theme.ResponsiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResponsiTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "register") {
                    composable("register") { RegisterScreen(navController) }
                    composable("main_menu") { MainMenuScreen(navController) }  // MainMenuScreen
                    composable("bmr_calculator") { BMRCalculatorScreen(navController) }
                    composable("user_data") { UserDataScreen(navController) }
                }
            }
        }
    }
}
