package com.example.onda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AdicaoSubtracaoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Fundo preto
            ) {
                AdicaoSubtracaoScreen()
            }
        }
    }
}

@Composable
fun AdicaoSubtracaoScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem OndaTec
        Image(
            painter = painterResource(id = R.drawable.ondatec),
            contentDescription = "OndaTec Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(16.dp),
            alignment = Alignment.Center
        )

        // Título
        Text(
            text = "Adição e Subtração",
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(top = 16.dp),
            textAlign = TextAlign.Center
        )

        // Linha horizontal
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(1.dp)
                .background(Color.White)
                .padding(vertical = 16.dp)
        )

        // Conteúdo
        Text(
            text = "Bem-vindo ao módulo de Adição e Subtração! Aqui você aprenderá os conceitos básicos com exemplos práticos.",
            color = Color.White,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

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
