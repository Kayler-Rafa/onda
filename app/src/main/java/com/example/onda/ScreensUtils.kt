package com.example.onda

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import androidx.compose.ui.draw.clip

@Composable
fun LoginScreen(
    userType: String,
    onLoginSuccess: (String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem superior (OndaTec)
        Image(
            painter = painterResource(id = R.drawable.ondatec),
            contentDescription = "OndaTec",
            modifier = Modifier.fillMaxWidth().height(100.dp)
        )

        // Imagem do Aluno/Professor
        Image(
            painter = painterResource(id = if (userType == "Aluno") R.drawable.aluno else R.drawable.professor),
            contentDescription = "$userType",
            modifier = Modifier.size(200.dp)
                .clip(CircleShape) // Formato circular
        )

        // Campos de entrada e botão de login
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Login",
                    fontSize = 24.sp,
                    color = Color.Black
                )

                // Campo de email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                // Campo de senha
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth()
                )

                // Botão de Login
                Button(
                    onClick = {
                        performLogin(userType, email, senha, onLoginSuccess) { error ->
                            errorMessage = error
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = if (userType == "Aluno") Color(0xFF6200EA) else Color(0xFF4CAF50)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "LOGIN", color = Color.White, fontSize = 16.sp)
                }

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

fun performLogin(
    userType: String,
    email: String,
    senha: String,
    onSuccess: (String) -> Unit,
    onError: (String) -> Unit
) {
    val url = if (userType == "Aluno") {
        "https://onda-tec-ia88wrett-rafael-dinizs-projects.vercel.app/alunos/login?email=$email&senha=$senha"
    } else {
        "https://onda-tec-ia88wrett-rafael-dinizs-projects.vercel.app/professores/login?email=$email&senha=$senha"
    }

    CoroutineScope(Dispatchers.IO).launch {
        try {
            val connection = URL(url).openConnection() as HttpURLConnection
            connection.requestMethod = "GET"

            val responseCode = connection.responseCode
            val responseBody = connection.inputStream.bufferedReader().use { it.readText() }

            withContext(Dispatchers.Main) {
                if (responseCode == 200 && responseBody.contains("\"success\":true")) {
                    onSuccess(email)
                } else if (responseBody.contains("Aluno não encontrado") || responseBody.contains("Professor não encontrado")) {
                    onError("Usuário não encontrado.")
                } else if (responseBody.contains("Senha incorreta")) {
                    onError("Senha incorreta.")
                } else {
                    onError("Algo deu errado, tente novamente mais tarde.")
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onError("Algo deu errado, tente novamente mais tarde.")
            }
        }
    }
}

@Composable
fun CadastroScreen(
    userType: String,
    onCadastroSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Reutiliza a lógica da tela de cadastro
        // Muito similar ao LoginScreen, mas muda a URL e o texto.
    }
}

