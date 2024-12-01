package com.example.onda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.onda.ui.theme.OndaTheme

class AlunoLoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OndaTheme {
                LoginScreen(userType = "Aluno") { email ->
                    // Login bem-sucedido
                    startActivity(Intent(this, AlunoDashboardActivity::class.java))
                    finish() // Finaliza a tela de login para não retornar
                }
            }
        }
    }
}