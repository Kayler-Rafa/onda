package com.example.onda

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

class AlunoCadastroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CadastroScreen(
                onCadastroSuccess = {
                    val intent = Intent(this, AlunoLoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            )
        }
    }
}
