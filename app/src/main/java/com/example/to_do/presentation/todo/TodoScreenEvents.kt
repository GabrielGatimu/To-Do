package com.example.to_do.presentation.todo

import com.example.to_do.data.Todo

sealed class TodoScreenEvents {
    object OnCreateTodoClicked: TodoScreenEvents()
    data class OnTodoClicked(val todo: Todo): TodoScreenEvents()
    data class OnSearchChanged(val searchQuery: String): TodoScreenEvents()
    data class OnIsCompleteChange(val todo: Todo, val isComplete: Boolean): TodoScreenEvents()
    data class OnDeleteTodo(val todo: Todo): TodoScreenEvents()
    object OnUndoDeleteTodo: TodoScreenEvents()
}