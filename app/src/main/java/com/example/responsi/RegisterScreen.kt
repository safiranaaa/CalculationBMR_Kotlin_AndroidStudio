package com.example.responsi

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment // Impor Alignment yang diperlukan
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current // Mendapatkan Context di dalam Composable

    // Membuat kolom utama dengan pengaturan padding dan spasi antar elemen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Agar elemen-elemen berada di tengah
        horizontalAlignment = Alignment.CenterHorizontally // Untuk meratakan secara horizontal
    ) {
        Text(
            text = "Daftar Akun",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp) // Memberikan jarak bawah
        )

        // TextField untuk username
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // TextField untuk password
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Gray
            )
        )

        // Button untuk melakukan aksi register
        Button(
            onClick = {
                // Simpan data dan pindah ke halaman BMR
                saveUserData(context, username, 0f) // Ganti 0f dengan nilai BMR yang dihitung
                navController.navigate("bmr_calculator")
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(
                text = "Register",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}

// Fungsi ini tidak perlu @Composable karena hanya berfungsi untuk menyimpan data
fun saveUserData(context: Context, username: String, bmr: Float) {
    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("username", username)
        putFloat("bmr", bmr)
        apply()
    }

    Log.d("USerData", "Username saved: $username, BMR saved: $bmr")
}