package com.example.responsi

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainMenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Main Menu",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Tombol untuk pergi ke kalkulator BMR
        Button(
            onClick = { navController.navigate("bmr_calculator") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        ) {
            Text("BMR Calculator")
        }

        // Tombol untuk melihat data user
        Button(
            onClick = { navController.navigate("user_data") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Lihat User Data")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainMenuScreen() {
    MainMenuScreen(navController = NavController(LocalContext.current))
}

