package com.example.primeiroapp.data

import android.content.Context
import androidx.room.Room

object DatabaseProvider {

    // Variável privada que guarda a instância do banco
    @Volatile
    private var INSTANCE: AppDatabase? = null

    // Função para obter a instância do banco (ou criar, se ainda não existir)
    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "meu_banco_de_dados" // nome do arquivo do banco local
            )
                .fallbackToDestructiveMigration() // recria o banco se houver mudança de versão
                .build()

            INSTANCE = instance
            instance
        }
    }
}
