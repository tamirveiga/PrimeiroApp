package com.example.primeiroapp

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.primeiroapp.data.PostagemDatabase
import com.example.primeiroapp.data.RepositorioPost
import kotlinx.coroutines.launch

@Composable
fun Aplicativo() {
    val navController = rememberNavController()

    // Inicializa o banco e o repositório
    val contexto = androidx.compose.ui.platform.LocalContext.current
    val database = remember { PostagemDatabase.getDatabase(contexto) }
    val repositorio = remember { RepositorioPost(database.postagemDao()) }

    // Observa as postagens em tempo real (LiveData)
    val postagens by repositorio.postagens.observeAsState(emptyList())

    Scaffold(
        bottomBar = { BarraInferior(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Tela.Inicio.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Tela.Inicio.name) {
                TelaInicial(navController, repositorio, postagens)
            }
            composable(Tela.NovaPostagem.name) {
                TelaNovaPostagem(navController, repositorio)
            }
            composable(Tela.Conta.name) {
                TelaConta()
            }
            composable(Tela.Configuracoes.name) {
                TelaConfiguracoes()
            }
        }
    }
}

enum class Tela {
    Inicio,
    NovaPostagem,
    Conta,
    Configuracoes
}
