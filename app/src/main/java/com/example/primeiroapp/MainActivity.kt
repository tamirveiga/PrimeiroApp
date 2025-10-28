package com.example.primeiroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
// import androidx.activity.viewModels // <-- Removido
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
// import com.example.primeiroapp.data.* // <-- Removido
import com.example.primeiroapp.ui.theme.PrimeiroAppTheme

class MainActivity : ComponentActivity() {

    // --- TUDO ISSO FOI REMOVIDO ---
    // private val database by lazy { ... }
    // private val repositorio by lazy { ... }
    // private val postagemViewModel: PostagemViewModel by viewModels { ... }
    // --- FIM DA REMOÇÃO ---

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimeiroAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // --- CORRIGIDO ---
                    // Apenas chame seu 'Aplicativo' Composable,
                    // que agora gerencia todo o estado.
                    Aplicativo()
                }
            }
        }
    }
}