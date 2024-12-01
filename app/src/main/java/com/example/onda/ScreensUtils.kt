package com.example.onda

import android.util.Log
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import org.json.JSONException
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import androidx.compose.ui.platform.LocalContext
import android.content.Context
import java.io.File
import java.io.FileWriter
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun LoginScreen(
    userType: String,
    onLoginSuccess: (String) -> Unit
) {
    val context = LocalContext.current // Obtenha o contexto para navegação
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.Black),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagem superior (OndaTec)
        Image(
            painter = painterResource(id = R.drawable.ondatec),
            contentDescription = "OndaTec",
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        // Imagem do Aluno/Professor
        Image(
            painter = painterResource(id = if (userType == "Aluno") R.drawable.aluno else R.drawable.professor),
            contentDescription = "$userType",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
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
                        Log.d("LoginFlow", "Botão de login pressionado")
                        performLogin(
                            userType = userType,
                            email = email,
                            senha = senha,
                            context = context,
                            onLoginSuccess = {
                                Log.d("LoginFlow", "Navegando para o Dashboard")
                                onLoginSuccess(userType) // Callback
                            },
                            onError = { error ->
                                errorMessage = error
                                Log.e("LoginFlow", "Erro: $error")
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (userType == "Aluno") Color(0xFF6200EA) else Color(0xFF4CAF50)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "LOGIN", color = Color.White, fontSize = 16.sp)
                }

                // Exibição de mensagens de erro
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
    context: Context,
    onLoginSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    // Construindo a URL com os parâmetros de email e senha
    val url = "https://onda-tec-ia88wrett-rafael-dinizs-projects.vercel.app/alunos/login?email=$email&senha=$senha"

    // Montando a requisição GET
    val request = Request.Builder()
        .url(url)
        .addHeader("Content-Type", "application/json") // Cabeçalho opcional
        .addHeader("userType", userType) // Incluindo userType se for necessário
        .build()

    // Realizando a requisição
    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("LoginFlow", "Erro na requisição: ${e.message}")
            onError("Erro ao conectar ao servidor. Verifique sua conexão.")
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) {
                    Log.e("LoginFlow", "Resposta não foi bem-sucedida: ${response.code}")
                    onError("Erro ao realizar login: Credenciais inválidas.")
                    return
                }

                try {
                    val responseBody = response.body?.string()
                    val json = JSONObject(responseBody ?: "")
                    val success = json.optBoolean("sucess", false)

                    if (success) {
                        Log.d("LoginFlow", "Login bem-sucedido")

                        // Salva o CSV com email e senha
                        try {
                            val file = File(context.filesDir, "user_credentials.csv")
                            val writer = FileWriter(file)
                            writer.append("$email,$senha")
                            writer.close()
                            Log.d("LoginFlow", "Credenciais salvas no CSV.")
                        } catch (e: IOException) {
                            Log.e("LoginFlow", "Erro ao salvar as credenciais no CSV: ${e.message}")
                        }

                        onLoginSuccess()
                    } else {
                        val message = json.optString("message", "Erro desconhecido.")
                        Log.e("LoginFlow", "Falha no login: $message")
                        onError(message)
                    }
                } catch (e: Exception) {
                    Log.e("LoginFlow", "Erro ao processar resposta: ${e.message}")
                    onError("Erro ao processar a resposta do servidor.")
                }
            }
        }
    })
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

