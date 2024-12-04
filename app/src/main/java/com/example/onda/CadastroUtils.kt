package com.example.onda

import android.content.Context
import android.util.Log
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
import androidx.compose.ui.platform.LocalContext
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

@Composable
fun CadastroScreen(
    onCadastroSuccess: () -> Unit
) {
    val context = LocalContext.current
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

        // Imagem do Aluno
        Image(
            painter = painterResource(id = R.drawable.aluno),
            contentDescription = "Cadastro",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        // Campos de entrada e botão de cadastro
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
                    text = "Cadastro",
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

                // Botão de Cadastro
                Button(
                    onClick = {
                        performCadastro(
                            email = email,
                            senha = senha,
                            context = context,
                            onCadastroSuccess = onCadastroSuccess,
                            onError = { error ->
                                errorMessage = error
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EA)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "CADASTRAR", color = Color.White, fontSize = 16.sp)
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

fun performCadastro(
    email: String,
    senha: String,
    context: Context,
    onCadastroSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val client = OkHttpClient()

    val url = "http://ec2-18-118-95-36.us-east-2.compute.amazonaws.com:3001/alunos/create?email=$email&senha=$senha"

    val request = Request.Builder()
        .url(url)
        .post(RequestBody.create(null, ByteArray(0))) // Corpo vazio
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            Log.e("CadastroScreen", "Erro na requisição: ${e.message}")
            CoroutineScope(Dispatchers.Main).launch {
                onError("Erro ao conectar ao servidor. Verifique sua conexão.")
            }
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) {
                    Log.e("CadastroScreen", "Resposta não foi bem-sucedida: ${response.code}")
                    CoroutineScope(Dispatchers.Main).launch {
                        onError("Erro ao realizar cadastro: ${response.message}")
                    }
                    return
                }

                val responseBody = response.body?.string()
                val json = JSONObject(responseBody ?: "")
                val success = json.optBoolean("success", false)

                CoroutineScope(Dispatchers.Main).launch {
                    if (success) {
                        onCadastroSuccess()
                    } else {
                        val message = json.optString("message", "Erro desconhecido.")
                        onError(message)
                    }
                }
            }
        }
    })
}
