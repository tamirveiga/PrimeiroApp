package com.example.primeiroapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.primeiroapp.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPerfil() {
    val contexto = LocalContext.current

    // Instâncias do banco e do repositório
    val database = DatabaseProvider.getDatabase(contexto)
    val repositorio = RepositorioPost(database.postagemDao())

    // ViewModel
    val viewModel: PostagemViewModel = viewModel(
        factory = PostagemViewModelFactory(repositorio)
    )

    // Observa postagens
    val listaPostagens by viewModel.postagens.observeAsState(emptyList())

    // Filtra apenas as postagens do usuário
    val usuarioAtual = "@usuario"
    val minhasPostagens = listaPostagens.filter { it.usuario == usuarioAtual }

    var textoPost by remember { mutableStateOf(TextFieldValue("")) }
    var postagemEmEdicao by remember { mutableStateOf<Postagem?>(null) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Perfil do Usuário", color = MaterialTheme.colorScheme.onPrimary) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho com ícone e nome do usuário
            Icon(
                Icons.Default.Person,
                contentDescription = "Perfil",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Text("Usuário Exemplo", style = MaterialTheme.typography.titleLarge)
            Text(usuarioAtual, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)

            Spacer(modifier = Modifier.height(24.dp))

            // Campo de texto
            OutlinedTextField(
                value = textoPost,
                onValueChange = { textoPost = it },
                label = { Text(if (postagemEmEdicao != null) "Editar postagem" else "Nova postagem") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Botão para salvar ou atualizar
            Button(
                onClick = {
                    val texto = textoPost.text.trim()
                    if (texto.isNotEmpty()) {
                        if (postagemEmEdicao != null) {
                            val atualizada = postagemEmEdicao!!.copy(conteudo = texto)
                            viewModel.atualizarPostagem(atualizada)
                            postagemEmEdicao = null
                        } else {
                            viewModel.adicionarPostagem("Usuário Exemplo", usuarioAtual, texto)
                        }
                        textoPost = TextFieldValue("")
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (postagemEmEdicao != null) "Atualizar" else "Publicar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de postagens do usuário
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(minhasPostagens) { postagem ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(postagem.conteudo, style = MaterialTheme.typography.bodyLarge)
                            Spacer(modifier = Modifier.height(8.dp))
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
