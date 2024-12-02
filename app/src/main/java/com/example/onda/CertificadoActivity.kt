package com.example.onda

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File
import java.io.FileOutputStream

class CertificadoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Fundo preto
            ) {
                CertificadoScreen(
                    onDownloadClick = { downloadCertificado() }
                )
            }
        }
    }

    private fun downloadCertificado() {
        try {
            // Carrega o recurso do drawable
            val bitmap = BitmapFactory.decodeResource(resources, R.drawable.certificadoexemplo)
            val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val outputFile = File(downloadsDir, "certificadoexemplo.png")

            // Salva o arquivo na pasta Downloads
            val outputStream = FileOutputStream(outputFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

            // Fechar streams
            outputStream.flush()
            outputStream.close()

            Toast.makeText(this, "Certificado salvo em Downloads", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Erro ao baixar o certificado", Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
fun CertificadoScreen(onDownloadClick: () -> Unit) {
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
                .size(200.dp)
                .padding(16.dp),
            contentScale = ContentScale.Fit
        )

        // Título e linha divisória
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Certificados",
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f) // 80% da largura da tela
                    .height(2.dp)
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(Color.White)
            )
        }

        // Lista de certificados
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color(0xFF1E1E1E), shape = RoundedCornerShape(8.dp)) // Card estilo TikTok
                .clickable { onDownloadClick() } // Ação de download
                .padding(16.dp)
        ) {
            // Título do card
            Text(
                text = "Certificado Exemplo",
                color = Color.White,
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Imagem do certificado
            Image(
                painter = painterResource(id = R.drawable.certificadoexemplo),
                contentDescription = "Certificado Exemplo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp), // Define o tamanho do "case"
                contentScale = ContentScale.Crop
            )
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
