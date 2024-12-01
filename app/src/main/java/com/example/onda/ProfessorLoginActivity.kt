package com.example.onda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.onda.ui.theme.OndaTheme
import androidx.compose.ui.Modifier

class ProfessorLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OndaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    LoginScreen(
                        userType = "Professor",
                        onLoginSuccess = { email ->
                            saveLogin(email)
                            startActivity(Intent(this, AlunoDashboardActivity::class.java))
                        }
                    )
                }
            }
        }
    }

    private fun saveLogin(email: String) {
        val sharedPref = getSharedPreferences("OndaTecPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("loggedInUser", email)
            apply()
        }
    }
}
