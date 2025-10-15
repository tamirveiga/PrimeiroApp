package com.example.primeiroapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.primeiroapp.data.Postagem

@Dao
interface PostagemDao {

    @Query("SELECT * FROM postagens ORDER BY id DESC")
    fun listarTodos(): Flow<List<Postagem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(postagem: Postagem)

    @Update
    suspend fun atualizar(postagem: Postagem)

    @Delete
    suspend fun deletar(postagem: Postagem)
}
