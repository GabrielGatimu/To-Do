package com.example.to_do.presentation.todo

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.data.Todo
import com.example.to_do.presentation.uiEvents.UiEvents
import com.example.to_do.domain.repository.TodoRepository
import com.example.to_do.presentation.screens.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel(){
    private var deletedTodo: Todo? = null

    private val _state = mutableStateOf(TodoScreenState())
    val state : State<TodoScreenState> = _state

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String> = _searchQuery

    private val _uiEvents = MutableSharedFlow<UiEvents>()
    val uiEvents: MutableSharedFlow<UiEvents> = _uiEvents

    init {
        getTodos()
    }

     fun getTodos() {
       repository.getAllTodo().onEach {
           _state.value = _state.value.copy(isLoading = true)
           _state.value = _state.value.copy(todos = it)
           _state.value = _state.value.copy(isLoading = false)
       }.launchIn(viewModelScope)
    }

    fun onSearch(){
        repository.getAllTodo().onEach {
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(todos = it.filter { it.title.contains(_searchQuery.value, ignoreCase = true) })
            _state.value = _state.value.copy(isLoading = false)
        }.launchIn(viewModelScope)
    }

    fun onEvent(todoScreenEvents: TodoScreenEvents){
        when(todoScreenEvents){
            is TodoScreenEvents.OnDeleteTodo -> {
                viewModelScope.launch {
                    deletedTodo = todoScreenEvents.todo
                    repository.deleteTodo(
                        todoScreenEvents.todo
                    )

                    _uiEvents.emit(UiEvents.OnShowSnackBar(
                        message = "Todo deleted!",
                        actions = "Undo"
                    ))

                }
            }

            is TodoScreenEvents.OnTodoClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnNavigate(
                        Screens.AddEditScreen.route + "?todoId=${todoScreenEvents.todo.id}"
                    ))
                }
            }

            is TodoScreenEvents.OnCreateTodoClicked -> {
                viewModelScope.launch {
                    _uiEvents.emit(UiEvents.OnNavigate(
                        Screens.AddEditScreen.route
                    ))
                }
            }

            is TodoScreenEvents.OnUndoDeleteTodo -> {
                deletedTodo?.let { todo ->
                    viewModelScope.launch {
                        repository.insertTodo(todo)
                    }
                }
            }

            is TodoScreenEvents.OnSearchChanged -> {
                _searchQuery.value = todoScreenEvents.searchQuery
            }

            is TodoScreenEvents.OnIsCompleteChange -> {
                viewModelScope.launch {
                    repository.insertTodo(
                        todoScreenEvents.todo.copy(
                            isComplete = todoScreenEvents.isComplete
                        )
                    )
                }
            }
        }
    }


}