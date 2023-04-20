package com.example.to_do.data

import com.example.to_do.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl constructor(
    private val todoDao: TodoDao
) : TodoRepository {
    override suspend fun insertTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    override fun getAllTodo(): Flow<List<Todo>> {
        return todoDao.getAllTodo()
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)
    }
}