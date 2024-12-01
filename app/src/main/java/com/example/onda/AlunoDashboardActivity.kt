package com.example.onda

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.onda.ui.theme.OndaTheme

class AlunoDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AlunoDashboardActivity", "AlunoDashboardActivity iniciada")
        setContent {
            OndaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black // Fundo preto
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Bem-vindo, Aluno!",
                            color = Color.White,
                            fontSize = 24.sp
                        )
                    }
                }
            }
        }
        try {
            // Configurações da tela
        } catch (e: Exception) {
            Log.e("AlunoDashboardActivity", "Erro ao carregar atividade: ${e.message}")
        }
    }
}
