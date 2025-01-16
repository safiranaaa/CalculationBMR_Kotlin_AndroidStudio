package com.example.responsi

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController

@Composable
fun BMRCalculatorScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
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

        // Input untuk Nama, Usia, Berat Badan, dan Tinggi Badan
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )

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
                if (name.isNotEmpty() && age.isNotEmpty() && weight.isNotEmpty() && height.isNotEmpty()) {
                    bmr = calculateBMR(age.toInt(), weight.toFloat(), height.toFloat(), gender)
                    // Simpan data dan pindah ke halaman UserData
                    saveUserBMRData(context, name, age.toInt(), weight.toFloat(), height.toFloat(), bmr)
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

fun saveUserBMRData(context: Context, name: String, age: Int, weight: Float, height: Float, bmr: Float) {
    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val userData = getUserData(context).toMutableList()
    userData.add(UserData(name, age, weight, height, bmr))
    // Save the list of user data
    editor.putString("userData", userData.joinToString(",") { "${it.name}|${it.age}|${it.weight}|${it.height}|${it.bmr}" })
    editor.apply()
}

data class UserData(val name: String, val age: Int, val weight: Float, val height: Float, val bmr: Float)

fun getUserData(context: Context): List<UserData> {
    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val userDataString = sharedPreferences.getString("userData", "") ?: ""
    return userDataString.split(",").mapNotNull {
        val parts = it.split("|")
        if (parts.size == 5) {
            UserData(parts[0], parts[1].toInt(), parts[2].toFloat(), parts[3].toFloat(), parts[4].toFloat())
        } else {
            null
        }
    }
}
