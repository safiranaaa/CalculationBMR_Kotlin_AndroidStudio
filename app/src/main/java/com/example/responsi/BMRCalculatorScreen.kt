package com.example.responsi

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun BMRCalculatorScreen(navController: NavHostController) {
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Pria") }
    var bmr by remember { mutableStateOf(0f) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "BMR Calculator",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Input untuk Usia, Berat Badan, dan Tinggi Badan
        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Usia (tahun)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Berat Badan (kg)") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Tinggi Badan (cm)") },
            modifier = Modifier.fillMaxWidth()
        )

        // Pilihan Jenis Kelamin
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == "Pria",
                    onClick = { gender = "Pria" }
                )
                Text("Pria")
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = gender == "Wanita",
                    onClick = { gender = "Wanita" }
                )
                Text("Wanita")
            }
        }

        // Tombol untuk menghitung BMR
        Button(
            onClick = {
                if (age.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty()) {
                    bmr = calculateBMR(age.toInt(), weight.toFloat(), height.toFloat(), gender)
                    // Simpan data dan pindah ke halaman UserData
                    saveUserBMRData(context, "Username", bmr) // Ganti "Username" dengan input user jika diperlukan
                    navController.navigate("user_data")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung BMR")
        }

        // Menampilkan nilai BMR yang dihitung
        if (bmr > 0) {
            Text(
                text = "BMR: ${String.format("%.2f", bmr)} kalori",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

fun calculateBMR(age: Int, weight: Float, height: Float, gender: String): Float {
    return if (gender == "Pria") {
        66 + (13.7f * weight) + (5f * height) - (6.8f * age)
    } else {
        655 + (9.6f * weight) + (1.8f * height) - (4.7f * age)
    }
}

fun saveUserBMRData(context: Context, username: String, bmr: Float) {
    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("username", username)
        putFloat("bmr", bmr)
        apply()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBMRCalculatorScreen() {
    BMRCalculatorScreen(navController = NavHostController(LocalContext.current))
}
