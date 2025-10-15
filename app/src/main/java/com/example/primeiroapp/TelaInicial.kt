package com.example.primeiroapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.primeiroapp.data.*
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TelaInicial() {
    val contexto = LocalContext.current

    // Banco de dados e repositório
    val database = PostagemDatabase.getDatabase(contexto)
    val repositorio = RepositorioPost(database.postagemDao())

    // ViewModel com Factory
    val viewModel: PostagemViewModel = viewModel(
        factory = PostagemViewModelFactory(repositorio)
    )

    // Observa as postagens do banco
    val listaPostagens by viewModel.postagens.collectAsStateWithLifecycle(initialValue = emptyList())


    // Estados para o campo de texto e edição
    var textoPost by remember { mutableStateOf(TextFieldValue("")) }
    var postagemEmEdicao by remember { mutableStateOf<Postagem?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val texto = textoPost.text.trim()
                    if (texto.isNotEmpty()) {
                        if (postagemEmEdicao != null) {
                            // Atualiza
                            val postAtualizado = postagemEmEdicao!!.copy(conteudo = texto)
                            viewModel.atualizarPostagem(postAtualizado)
                            postagemEmEdicao = null
                        } else {
                            // Adiciona
                            viewModel.adicionarPostagem("Usuário", "@usuario", texto)
                        }
                        textoPost = TextFieldValue("")
                    }
                },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Postagens",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Campo de texto para criar/editar postagem
            OutlinedTextField(
                value = textoPost,
                onValueChange = { textoPost = it },
                label = { Text(if (postagemEmEdicao != null) "Editar Postagem" else "Nova Postagem") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de postagens
            LazyColumn {
                items(listaPostagens) { postagem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = postagem.autor,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = postagem.conteudo,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(onClick = {
                                    textoPost = TextFieldValue(postagem.conteudo)
                                    postagemEmEdicao = postagem
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }

                                IconButton(onClick = {
                                    viewModel.deletarPostagem(postagem)
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Excluir")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
