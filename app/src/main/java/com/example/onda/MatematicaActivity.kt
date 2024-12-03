package com.example.onda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

class MatematicaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black // Fundo preto
            ) {
                MatematicaScreen(
                    onAdicaoSubtracaoClick = {
                        startActivity(Intent(this, AdicaoSubtracaoActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
fun MatematicaScreen(onAdicaoSubtracaoClick: () -> Unit) {
    var showMaintenanceMessage by remember { mutableStateOf(false) }
    var maintenanceMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
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
            text = "Matemática",
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

        // Abas
        val sections = listOf(
            "Módulo 1" to listOf(
                "Adição e Subtração" to "Conceitos básicos para somar e subtrair.",
                "Exemplo" to "",
                "Exemplo" to "",
                "Exemplo" to ""
            ),
            "Módulo 2" to listOf(
                "Novos conteúdos serão adicionados em breve." to null
            ),
            "Módulo 3" to listOf(
                "Novos conteúdos serão adicionados em breve." to null
            )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            sections.forEach { (title, items) ->
                ExpandableSectionMatematica(
                    title = title,
                    items = items,
                    onAdicaoSubtracaoClick = onAdicaoSubtracaoClick,
                    onShowMaintenanceMessage = {
                        maintenanceMessage = it
                        showMaintenanceMessage = true
                    }
                )
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

    // Exibe mensagem de manutenção, se necessário
    if (showMaintenanceMessage) {
        AlertDialog(
            onDismissRequest = { showMaintenanceMessage = false },
            title = { Text(text = "Aviso", color = Color.Black) },
            text = { Text(text = maintenanceMessage, color = Color.Black) },
            confirmButton = {
                Button(onClick = { showMaintenanceMessage = false }) {
                    Text("Ok")
                }
            },
            modifier = Modifier.background(Color.White)
        )
    }
}

@Composable
fun ExpandableSectionMatematica(
    title: String,
    items: List<Pair<String, String?>>,
    onAdicaoSubtracaoClick: () -> Unit,
    onShowMaintenanceMessage: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 18.sp,
            modifier = Modifier.padding(8.dp)
        )

        if (expanded) {
            items.forEach { (itemTitle, description) ->
                if (description != null) {
                    Card(
                        onClick = {
                            if (itemTitle == "Adição e Subtração") {
                                onAdicaoSubtracaoClick()
                            } else {
                                onShowMaintenanceMessage("Novos conteúdos serão adicionados em breve. Volte novamente mais tarde.")
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(Color.Yellow, shape = RoundedCornerShape(8.dp))
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = itemTitle,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                if (description.isNotEmpty()) {
                                    Text(
                                        text = description,
                                        color = Color.Gray,
                                        fontSize = 12.sp
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = itemTitle,
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}

