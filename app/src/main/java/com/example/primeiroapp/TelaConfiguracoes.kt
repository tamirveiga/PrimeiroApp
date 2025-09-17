package com.example.primeiroapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaConfiguracoes() {
    Scaffold(
        containerColor = Color(0xFF0D1117),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Configurações", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0D1117),
                    titleContentColor = Color.White
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            ItemConfig("Sua conta")
            ItemConfig("Monetização")
            ItemConfig("Premium")
            ItemConfig("Assinaturas do Criador")
            ItemConfig("Segurança e acesso à conta")
            ItemConfig("Privacidade e segurança")
            ItemConfig("Notificações")
            ItemConfig("Acessibilidade, exibição e idiomas")
            ItemConfig("Recursos adicionais")
            ItemConfig("Central de Ajuda")
        }
    }
}

@Composable
fun ItemConfig(texto: String) {
    Card(
        colors = CardDefaults.cardColors(Color.Transparent),
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { /* ação ao clicar */ },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = texto,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Icon(Icons.Default.ArrowForward, contentDescription = "Ir", tint = Color.Gray)
        }
    }
}