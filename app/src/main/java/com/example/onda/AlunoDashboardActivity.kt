package com.example.onda

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
                AlunoDashboardScreen(
                    onLogout = {
                        val context = applicationContext
                        val file = File(context.filesDir, "user_credentials.csv")
                        if (file.exists()) {
                            file.delete() // Apaga o CSV
                            Log.d("AlunoDashboard", "Arquivo CSV deletado.")
                        }
                        // Redireciona para a tela inicial
                        startActivity(Intent(this, MainActivity::class.java))
                        finish() // Fecha a tela de Dashboard
                    },
                    onCertificadoClick = {
                        startActivity(Intent(this, CertificadoActivity::class.java))
                    },
                    onEnsinosClick = {
                        startActivity(Intent(this, EnsinosActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun AlunoDashboardScreen(
    onLogout: () -> Unit,
    onCertificadoClick: () -> Unit,
    onEnsinosClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Imagem superior
        Image(
            painter = painterResource(id = R.drawable.ondatec), // Substitua por seu recurso de imagem
            contentDescription = "OndaTec Logo",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        // Imagem central
        Image(
            painter = painterResource(id = R.drawable.livro_icon), // Substitua por seu recurso de imagem
            contentDescription = "Livro",
            modifier = Modifier
                .size(300.dp)
                .clip(CircleShape)
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        // Botões
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onCertificadoClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Certificado", color = Color.Black)
            }

            Button(
                onClick = onEnsinosClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)), // Roxo
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ensinos", color = Color.White)
            }

            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Logout", color = Color.White)
            }
        }

        // Rodapé
        Text(
            text = "OndaTec",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 16.dp),
            textAlign = TextAlign.Center
        )
    }
}
