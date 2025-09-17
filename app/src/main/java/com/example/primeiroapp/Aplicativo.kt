package com.example.primeiroapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Aplicativo() {
    val navController = rememberNavController()
    val postagens = RepositorioPost.postagens

    Scaffold(
        containerColor = Color(0xFF0D1117),
        bottomBar = { BarraInferior(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Tela.Inicio.name,
            modifier = Modifier.padding(padding)
        ) {
            composable(Tela.Inicio.name) {
                TelaInicial(navController, postagens)
            }
            composable(Tela.Configuracoes.name) {
                TelaConfiguracoes()
            }
            composable(Tela.Conta.name) {
                TelaConta()
            }
            composable(Tela.NovoTweet.name) {
                TelaNovoTweet(navController)
            }
        }
    }
}