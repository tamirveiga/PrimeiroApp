package com.example.primeiroapp

import androidx.compose.runtime.mutableStateListOf

data class Postagem(
    val id: Int,
    val autor: String,
    val usuario: String,
    val conteudo: String,
    val curtidas: Int = 0,
    val comentarios: Int = 0,
    val compartilhamentos: Int = 0
)

object RepositorioPost {
    private val _postagens = mutableStateListOf<Postagem>()
    val postagens: List<Postagem> get() = _postagens

    init {
        _postagens.addAll(
            listOf(
                Postagem(
                    id = 1,
                    autor = "Usuário Exemplo",
                    usuario = "@usuario",
                    conteudo = "Meu primeiro tweet!",
                    curtidas = 12,
                    comentarios = 5,
                    compartilhamentos = 3
                ),
                Postagem(
                    id = 2,
                    autor = "Fulana",
                    usuario = "@fulana",
                    conteudo = "Apenas 3 meses para o Natal!",
                    curtidas = 50,
                    comentarios = 10,
                    compartilhamentos = 15
                )
            )
        )
    }

    fun addPost(autor: String, usuario: String, conteudo: String) {
        val newId = _postagens.size + 1
        _postagens.add(
            0,
            Postagem(
                id = newId,
                autor = autor,
                usuario = usuario,
                conteudo = conteudo
            )
        )
    }
}