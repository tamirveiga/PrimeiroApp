package com.example.primeiroapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.primeiroapp.data.Postagem

// Define o banco de dados e as entidades
@Database(entities = [Postagem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postagemDao(): PostagemDao
}
