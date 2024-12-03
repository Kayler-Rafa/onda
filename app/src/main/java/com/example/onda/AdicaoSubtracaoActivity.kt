package com.example.onda

import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.viewinterop.AndroidView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
// import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.PlayerConstants

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

        // Linha horizontal
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.White)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Seção de Videoaula
        SectionTitle(title = "Videoaula")
        Spacer(modifier = Modifier.height(16.dp))
        YouTubeVideoPlayer(videoId = "e78_5WIssSU")

        Spacer(modifier = Modifier.height(32.dp))

        // Seção de Monitorias
        SectionTitle(title = "Monitorias")
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "Nenhuma sala de monitoria disponível no momento. Tente novamente mais tarde.",
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Seção de Resumo
        SectionTitle(title = "Resumo")
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "A adição e subtração são as operações matemáticas básicas que envolvem somar ou subtrair números. A adição combina dois ou mais valores para formar um total, enquanto a subtração calcula a diferença entre dois números. Essas operações são fundamentais para o aprendizado matemático e têm aplicações práticas no dia a dia, como no cálculo de troco, gerenciamento de tempo e controle de despesas.",
                color = Color.White,
                textAlign = TextAlign.Justify
            )
        }

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

@Composable
fun SectionTitle(title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(1.dp)
                .background(Color.White)
                .padding(vertical = 8.dp)
        )
    }
}

@Composable
fun YouTubeVideoPlayer(videoId: String) {
    AndroidView(
        factory = { context ->
            YouTubePlayerView(context).apply {
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0f)
                    }

                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                        Toast.makeText(context, "Erro ao carregar o vídeo", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

