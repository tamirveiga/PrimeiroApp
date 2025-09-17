package com.example.primeiroapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaInicial(navController: NavController, posts: List<Postagem>) {
    Scaffold(
        containerColor = Color(0xFF0D1117),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Início", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(0xFF0D1117),
                    titleContentColor = Color.White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Tela.NovoTweet.name) },
                containerColor = Color(0xFF1D9BF0)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Novo Tweet")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            items(posts) { post ->
                PostagemUI(post)
                Divider(color = Color.Gray, thickness = 0.5.dp)
            }
        }
    }
}