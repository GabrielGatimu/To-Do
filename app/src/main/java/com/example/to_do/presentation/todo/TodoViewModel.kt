package com.example.to_do.presentation.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.to_do.presentation.uiEvents.UiEvents
import com.example.to_do.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
){
    val todos = repository.getAllTodo()

    var searchQuery by mutableStateOf("")

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: MutableSharedFlow<UiEvents> = _uiEvents

    fun onEvent(todoScreenEvents: TodoScreenEvents){
        when(todoScreenEvents){
            is TodoScreenEvents.OnDeleteTodo -> {}

            is TodoScreenEvents.OnTodoClicked -> {}

            is TodoScreenEvents.OnCreateTodoClicked -> {}

            is TodoScreenEvents.OnUndoDeleteTodo -> {}

            is TodoScreenEvents.OnSearchChanged -> {
                searchQuery = todoScreenEvents.searchQuery
            }

            is TodoScreenEvents.OnSearchClicked -> {}

        }
    }


}