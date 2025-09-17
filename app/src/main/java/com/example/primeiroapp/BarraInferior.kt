package com.example.primeiroapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class Tela {
    Inicio,
    Conta,
    Configuracoes,
    NovoTweet
}

@Composable
fun BarraInferior(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        Triple(Icons.Default.Home, "Início", Tela.Inicio.name),
        Triple(Icons.Default.Settings, "Configurações", Tela.Configuracoes.name),
        Triple(Icons.Default.Person, "Conta", Tela.Conta.name)
    )

    NavigationBar(containerColor = Color(0xFF0D1117)) {
        items.forEach { (icon, label, route) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = label, tint = if (currentRoute == route) Color.White else Color.Gray) },
                selected = currentRoute == route,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}