package com.example.to_do.presentation.todo

sealed class TodoScreenEvents {
    object OnCreateTodoClicked: TodoScreenEvents()

    data class OnTodoClicked(val id: String): TodoScreenEvents()

    data class OnSearchChanged(val searchQuery: String): TodoScreenEvents()

    object OnSearchClicked: TodoScreenEvents()

    object OnDeleteTodo: TodoScreenEvents()

    object OnUndoDeleteTodo: TodoScreenEvents()
}