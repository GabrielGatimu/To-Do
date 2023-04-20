package com.example.to_do.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import kotlin.collections.List as List

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("SELECT * FROM todo")
    fun getAllTodo(): Flow<List<Todo>>

    @Query("SELECT * FROM todo WHERE ID=:id")
    suspend fun getTodoById(id: Int): Todo?
}