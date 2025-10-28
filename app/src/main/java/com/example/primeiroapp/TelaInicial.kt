package com.example.primeiroapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue // Este import está correto
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.primeiroapp.PostagemUI
import com.example.primeiroapp.data.Postagem
import com.example.primeiroapp.data.PostagemViewModel // <-- IMPORTANTE
// Imports desnecessários (scope, repositorio) removidos

@SuppressLint("UnusedMaterial3_ScaffoldPaddingParameter") // Corrigi o nome do lint
@Composable
fun TelaInicial(
    // 1. Recebendo o ViewModel (Gerente), não mais o Repositório
    navController: NavHostController,
    viewModel: PostagemViewModel,
    postagens: List<Postagem>
) {

    var textoPost by remember { mutableStateOf(TextFieldValue("")) }
    var postagemEmEdicao by remember { mutableStateOf<Postagem?>(null) }

    // 2. O 'scope = rememberCoroutineScope()' foi REMOVIDO

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val texto = textoPost.text.trim()
                    if (texto.isNotEmpty()) {
                        if (postagemEmEdicao != null) {
                            // 3. Chamando a função "limpa" do ViewModel
                            val postAtualizado = postagemEmEdicao!!.copy(conteudo = texto)
                            viewModel.atualizarPostagem(postAtualizado) // <-- CORRIGIDO
                            postagemEmEdicao = null
                        } else {
                            // 4. Chamando a função "limpa" do ViewModel
                            viewModel.adicionarPostagem("Usuário", "@usuario", texto) // <-- CORRIGIDO
                        }
                        textoPost = TextFieldValue("")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // ... O RESTO DA SUA INTERFACE (TEXT, SPACER, ETC) FICA IGUAL ...
            Text(
                text = "Postagens",
                style = MaterialTheme.typography.headlineSmall, // Adicionei o style de volta
                modifier = Modifier.padding(bottom = 8.dp) // Adicionei o modifier de volta
            )

            // ----- CORRIGIDO AQUI -----
            // O erro 'None of the following candidates' era porque
            // seu OutlinedTextField estava sem os parâmetros 'onValueChange' e 'label'.
            OutlinedTextField(
                value = textoPost,
                onValueChange = { textoPost = it },
                label = { Text(if (postagemEmEdicao != null) "Editar Postagem" else "Nova Postagem") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // A lista já usava 'postagens', então está perfeita
            LazyColumn {
                items(postagens) { postagem ->
                    PostagemUI(post = postagem,
                        onDeleteClicked = { postParaExcluir ->
                            viewModel.deletarPostagem(postParaExcluir)
                        }
                    )
                }
            }
        }
    }
}