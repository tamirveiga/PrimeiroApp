package com.example.primeiroapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.primeiroapp.data.PostagemViewModel // <-- IMPORTANTE
// Imports desnecessários (scope, repositorio) removidos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaNovoTweet(
    navController: NavController,
    // 1. Recebendo o ViewModel (Gerente)
    viewModel: PostagemViewModel
) {
    var tweetText by remember { mutableStateOf("") }
    val maxChars = 280

    // 2. O 'scope = rememberCoroutineScope()' foi REMOVIDO

    Scaffold(
        containerColor = Color(0xFF0D1117),
        topBar = {
            CenterAlignedTopAppBar(
                title = { /* vazio */ },
                navigationIcon = {
                    //... (Interface intacta)
                },
                actions = {
                    Button(
                        onClick = {
                            if (tweetText.isNotBlank()) {
                                // 3. Chamando a função "limpa" do ViewModel
                                viewModel.adicionarPostagem("Usuário Exemplo", "@usuario", tweetText) // <-- CORRIGIDO
                                navController.popBackStack()
                            }
                        },
                        //... (Interface do botão intacta)
                    ) {
                        Text("Tweet")
                    }
                },
                //... (Interface do TopBar intacta)
            )
        }
    ) { padding ->
        // ... (Toda a sua interface do Column/Row/OutlinedTextField está intacta)
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = androidx.compose.ui.Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Icon(Icons.Default.Person, contentDescription = "Perfil", tint = Color.White, modifier = Modifier.size(48.dp))
                OutlinedTextField(
                    value = tweetText,
                    onValueChange = { if (it.length <= maxChars) tweetText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 150.dp),
                    placeholder = { Text("O que está acontecendo?", color = Color.Gray) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    )
                )
            }
        }
    }
}