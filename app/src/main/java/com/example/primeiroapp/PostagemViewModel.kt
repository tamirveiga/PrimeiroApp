package com.example.primeiroapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PostagemViewModel(private val repositorio: RepositorioPost) : ViewModel() {

    val postagens: LiveData<List<Postagem>> = repositorio.postagens

    fun adicionarPostagem(autor: String, usuario: String, conteudo: String) {
        viewModelScope.launch {
            repositorio.adicionarPostagem(autor, usuario, conteudo)
        }
    }

    fun atualizarPostagem(postagem: Postagem) {
        viewModelScope.launch {
            repositorio.atualizarPostagem(postagem)
        }
    }

    fun deletarPostagem(postagem: Postagem) {
        viewModelScope.launch {
            repositorio.excluirPostagem(postagem)
        }
    }
}
