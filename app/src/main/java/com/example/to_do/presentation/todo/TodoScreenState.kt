package com.example.to_do.presentation.todo

import com.example.to_do.data.Todo

data class TodoScreenState (
    val isLoading: Boolean = false,
    val todos: List<Todo> = emptyList()
)
