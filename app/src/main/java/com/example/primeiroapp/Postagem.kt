package com.example.primeiroapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Representa uma postagem no aplicativo.
 * Essa classe é usada pelo Room para criar a tabela 'postagens'.
 */
@Entity(tableName = "postagens")
data class Postagem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val autor: String,
    val usuario: String,
    val conteudo: String,
    val curtidas: Int = 0,
    val comentarios: Int = 0,
    val compartilhamentos: Int = 0
)
