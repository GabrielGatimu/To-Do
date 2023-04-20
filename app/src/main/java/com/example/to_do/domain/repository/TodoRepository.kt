package com.example.to_do.domain.repository

import com.example.to_do.data.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun insertTodo(todo: Todo)

    suspend fun deleteTodo(todo: Todo)

    fun getAllTodo(): Flow<List<Todo>>

    suspend fun getTodoById(id: Int): Todo?
}