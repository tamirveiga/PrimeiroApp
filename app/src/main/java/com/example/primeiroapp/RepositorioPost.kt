package com.example.primeiroapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RepositorioPost(private val dao: PostagemDao) {

    // Retorna todas as postagens como LiveData (para atualização automática na tela)
    val postagens: LiveData<List<Postagem>> = dao.listarTodos().asLiveData()

    // Cria um novo post no banco de dados
    fun adicionarPostagem(autor: String, usuario: String, conteudo: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val novaPostagem = Postagem(
                autor = autor,
                usuario = usuario,
                conteudo = conteudo
            )
            dao.inserir(novaPostagem)
        }
    }

    // Atualiza um post existente
    fun atualizarPostagem(postagem: Postagem) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.atualizar(postagem)
        }
    }

    // Exclui um post
    fun excluirPostagem(postagem: Postagem) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deletar(postagem)
        }
    }
}
