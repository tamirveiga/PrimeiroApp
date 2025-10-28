package com.example.primeiroapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue // 1. O import correto (mantido)
import androidx.compose.ui.unit.dp
import com.example.primeiroapp.PostagemUI
import com.example.primeiroapp.data.Postagem
import com.example.primeiroapp.data.PostagemViewModel
// ... (Todos os outros imports da sua interface, como Box, CircleShape, etc. ficam aqui)
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
// import androidx.compose.ui.text.input.TextFieldValue // 2. O import duplicado (REMOVIDO)
import androidx.compose.ui.unit.sp // 3. O import que faltava (ADICIONADO)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaPerfil(
    // 1. Recebendo o ViewModel, não mais o Repositório
    viewModel: PostagemViewModel,
    postagens: List<Postagem>
) {

    val usuarioAtual = "@usuario"
    val minhasPostagens = postagens.filter { it.usuario == usuarioAtual }

    var textoPost by remember { mutableStateOf(TextFieldValue("")) }
    var postagemEmEdicao by remember { mutableStateOf<Postagem?>(null) }

    // 2. O 'scope = rememberCoroutineScope()' foi REMOVIDO

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ... TODO O SEU CABEÇALHO PERFEITO FICA AQUI ...
            // (Spacer, Box, Icon, Button("Editar perfil"), Text, Row, etc...)

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Imagem de Capa (Banner)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.Gray) // Um placeholder para o banner
                )

                // Foto de Perfil (sobreposta)
                Icon(
                    Icons.Default.Person, // Use seu ícone ou Painter
                    contentDescription = "Foto de Perfil",
                    tint = Color.White,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.DarkGray)
                        .border(4.dp, MaterialTheme.colorScheme.background, CircleShape)
                        .align(Alignment.BottomStart) // Alinha na parte inferior esquerda
                        .offset(y = 40.dp) // "Flutua" para baixo
                )
            }

            // Botão "Editar Perfil" (alinhado à direita)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp), // Espaço para a foto de perfil "flutuante"
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /* TODO */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Text("Editar perfil", fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(24.dp)) // Espaço extra

            // Informações do Usuário
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start // Alinha textos à esquerda
            ) {
                // Agora o .sp não dará mais erro
                Text("Usuário Exemplo", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text(usuarioAtual, fontSize = 16.sp, color = Color.Gray)

                Spacer(modifier = Modifier.height(16.dp))

                // Seguidores e Seguindo
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    Row {
                        Text("120", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Seguindo", color = Color.Gray)
                    }
                    Row {
                        Text("55", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Seguidores", color = Color.Gray)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 0.5.dp)

            // ... FIM DO CABEÇALHO ...

            // Botão para salvar ou atualizar
            OutlinedTextField(
                value = textoPost,
                onValueChange = { textoPost = it }, // Isso agora está correto
                label = { Text(if (postagemEmEdicao != null) "Editar Postagem" else "Nova Postagem") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    val texto = textoPost.text.trim()
                    if (texto.isNotEmpty()) {
                        if (postagemEmEdicao != null) {
                            // 3. Chamando a função "limpa" do ViewModel
                            val atualizada = postagemEmEdicao!!.copy(conteudo = texto)
                            viewModel.atualizarPostagem(atualizada) // <-- CORRIGIDO
                            postagemEmEdicao = null
                        } else {
                            // 4. Chamando a função "limpa" do ViewModel
                            viewModel.adicionarPostagem("Usuário Exemplo", usuarioAtual, texto) // <-- CORRIGIDO
                        }
                        textoPost = TextFieldValue("")
                    }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (postagemEmEdicao != null) "Atualizar" else "Publicar")
            }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray, thickness = 0.5.dp)

            // Lista de postagens do usuário
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(minhasPostagens) { postagem ->
                    PostagemUI(
                        post = postagem,
                        onDeleteClicked = { postParaExcluir ->
                            // A 'minhasPostagens' já é filtrada, então é seguro excluir
                            viewModel.deletarPostagem(postParaExcluir)
                        }
                    )
                    Divider(color = Color.Gray, thickness = 0.5.dp)
                }
            }
        }
    }
}