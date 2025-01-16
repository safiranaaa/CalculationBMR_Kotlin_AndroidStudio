package com.example.responsi

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun UserDataScreen(navController: NavHostController) {
    val userData = getUserData(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "User Data",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Tampilkan semua data yang disimpan
        userData.forEach { data ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("Nama: ${data.name}")
                    Text("Usia: ${data.age}")
                    Text("Berat Badan: ${data.weight} kg")
                    Text("Tinggi Badan: ${data.height} cm")
                    Text("BMR: ${data.bmr} kalori")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk kembali ke halaman utama
        Button(
            onClick = {
                navController.navigate("main_menu")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali ke Main Menu")
        }
    }
}
