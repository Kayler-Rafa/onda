package com.example.onda

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

class AlunoDashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AlunoDashboardActivity", "AlunoDashboardActivity iniciada")
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Fundo preto
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Bem-vindo, Aluno!",
                        color = Color.White,
                        fontSize = 24.sp
                    )

                    // Botão de logout
                    Button(
                        onClick = {
                            val context = applicationContext
                            val file = File(context.filesDir, "user_credentials.csv")
                            if (file.exists()) {
                                // Apaga o CSV
                                file.delete()
                                Log.d("AlunoDashboard", "Arquivo CSV deletado.")
                            }

                            // Redireciona para a tela inicial
                            startActivity(Intent(this@AlunoDashboardActivity, MainActivity::class.java))
                            finish() // Fecha a tela de Dashboard
                        },
                        modifier = Modifier.padding(top = 16.dp)
                    ) {
                        Text(text = "Logout", color = Color.White)
                    }
                }
            }
        }
    }
}
