package com.example.onda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

class EnsinosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Fundo preto
            ) {
                EnsinosScreen(
                    onMateriasClick = {
                        startActivity(Intent(this, MateriasActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun EnsinosScreen(onMateriasClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem superior (OndaTec)
        Image(
            painter = painterResource(id = R.drawable.ondatec),
            contentDescription = "OndaTec Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .padding(16.dp),
            alignment = Alignment.Center
        )



        // Quadrado com borda branca
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .border(width = 2.dp, color = Color.White)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Imagem Brasil
                Image(
                    painter = painterResource(id = R.drawable.brasil),
                    contentDescription = "Imagem Brasil",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    alignment = Alignment.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Texto Visão
                Text(
                    text = "Visão",
                    color = Color.White,
                    fontSize = 20.sp, // Título maior
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Texto Objetivos
                Text(
                    text = "Objetivos da OndaTec",
                    color = Color.White,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Espaço vazio
                Spacer(modifier = Modifier.height(16.dp))

                // Texto descritivo
                Text(
                    text = "Através da atuação da empresa Onda Tec, cidadãos brasileiros terão a oportunidade de romper o ciclo de desigualdade e ter acesso a uma educação de qualidade.\n" +
                            "Ao apoiar essa empresa inovadora, você estará contribuindo para a construção de um país mais justo, onde todos os brasileiros tenham igualdade de oportunidades.\n" +
                            "Juntos, podemos impulsionar a missão da Onda Tec de democratizar o acesso à educação, proporcionando as ferramentas necessárias para transformar vidas. Ao apoiar essa causa, estaremos construindo um futuro no qual indígenas, crianças em situação de abandono e pessoas com deficiência possam desenvolver todo o seu potencial.",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }

        // Botão Matérias
        Button(
            onClick = { onMateriasClick() },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.5f)
                .height(50.dp)
        ) {
            Text(text = "Matérias", color = Color.White)
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
