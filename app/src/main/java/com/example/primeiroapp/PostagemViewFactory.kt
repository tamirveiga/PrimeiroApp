package com.example.primeiroapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PostagemViewModelFactory(private val repositorio: RepositorioPost) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostagemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PostagemViewModel(repositorio) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida")
    }
}
