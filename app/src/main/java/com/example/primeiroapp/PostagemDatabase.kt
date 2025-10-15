package com.example.primeiroapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.primeiroapp.data.Postagem

@Database(entities = [Postagem::class], version = 1, exportSchema = false)
abstract class PostagemDatabase : RoomDatabase() {

    abstract fun postagemDao(): PostagemDao

    companion object {
        @Volatile
        private var INSTANCE: PostagemDatabase? = null

        fun getDatabase(context: Context): PostagemDatabase {
            // Se o banco já foi criado, retorna ele
            return INSTANCE ?: synchronized(this) {
                // Se ainda não foi criado, cria e guarda na variável INSTANCE
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PostagemDatabase::class.java,
                    "postagem_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
