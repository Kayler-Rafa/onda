package com.example.onda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.onda.ui.theme.OndaTheme

class AlunoLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OndaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    LoginScreen(
                        userType = "Aluno",
                        onLoginSuccess = { email ->
                            Log.d("LoginFlow", "onLoginSuccess chamado com email: $email")
                            try {
                                val intent = Intent(this, AlunoDashboardActivity::class.java)
                                intent.putExtra("userEmail", email)
                                Log.d("LoginFlow", "Iniciando AlunoDashboardActivity com Intent")
                                startActivity(intent)
                                finish() // Fecha a tela de login
                            } catch (e: Exception) {
                                Log.e("LoginFlow", "Erro ao redirecionar: ${e.message}")
                            }
                        }
                    )
                }
            }
        }
    }
}
