package com.example.primeiroapp.data // (Verifique se este é o pacote correto)

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Este é o "Gerente" (ViewModel).
 * Ele é o único que fala com o Repositório (a "Cozinha").
 * A Tela (View) SÓ fala com ele.
 */
class PostagemViewModel(private val repositorio: RepositorioPost) : ViewModel() {

    // 1. Ele expõe a lista de postagens para a Tela
    val postagens: LiveData<List<Postagem>> = repositorio.postagens

    // 2. Ele cria funções "limpas" para a Tela chamar.
    // O 'viewModelScope' fica escondido aqui dentro.

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

    // (Vou usar 'deletarPostagem' como o nome da função no ViewModel
    // para chamar 'excluirPostagem' do repositório)
    fun deletarPostagem(postagem: Postagem) {
        viewModelScope.launch {
            repositorio.excluirPostagem(postagem)
        }
    }
}