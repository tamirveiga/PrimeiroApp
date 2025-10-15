package com.example.primeiroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.primeiroapp.data.*
import com.example.primeiroapp.ui.theme.PrimeiroAppTheme

class MainActivity : ComponentActivity() {

    // Instancia o banco e o repositório
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repositorio by lazy { RepositorioPost(database.postagemDao()) }

    // Cria o ViewModel com o repositório
    private val postagemViewModel: PostagemViewModel by viewModels {
        PostagemViewModelFactory(repositorio)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimeiroAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    // Aqui você chama seu NavHost ou a tela inicial,
                    // passando o ViewModel como parâmetro
                    NavegacaoPrincipal(viewModel = postagemViewModel)
                }
            }
        }
    }
}
