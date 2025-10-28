package com.example.primeiroapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel // <-- IMPORTANTE
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.primeiroapp.data.PostagemDatabase
import com.example.primeiroapp.data.PostagemViewModel
import com.example.primeiroapp.data.PostagemViewModelFactory
import com.example.primeiroapp.data.RepositorioPost
import com.example.primeiroapp.BarraInferior
import com.example.primeiroapp.Tela
import com.example.primeiroapp.TelaConfiguracoes
import com.example.primeiroapp.ui.TelaInicial
import com.example.primeiroapp.ui.TelaPerfil

@Composable
fun Aplicativo() {
    val navController = rememberNavController()
    val contexto = androidx.compose.ui.platform.LocalContext.current

    // 1. Criamos o Repositório (Cozinha)
    val repositorio = remember { RepositorioPost(PostagemDatabase.getDatabase(contexto).postagemDao()) }

    // 2. CRIAMOS O VIEWMODEL (Gerente) usando o Repositório
    val viewModel: PostagemViewModel = viewModel(
        factory = PostagemViewModelFactory(repositorio)
    )

    // 3. Observamos as postagens vindas do ViewModel, não mais do repositório
    val postagens by viewModel.postagens.observeAsState(emptyList())

    Scaffold(
        bottomBar = { BarraInferior(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Tela.Inicio.name,
            modifier = Modifier.padding(paddingValues)
        ) {

            // 4. Entregamos o ViewModel (Gerente) para as telas

            composable(Tela.Inicio.name) {
                TelaInicial(navController, viewModel, postagens)
            }
            composable(Tela.NovoTweet.name) { // (Rota corrigida)
                TelaNovoTweet(navController, viewModel)
            }
            composable(Tela.Conta.name) {
                TelaPerfil(viewModel, postagens)
            }
            composable(Tela.Configuracoes.name) {
                TelaConfiguracoes()
            }
        }
    }
}