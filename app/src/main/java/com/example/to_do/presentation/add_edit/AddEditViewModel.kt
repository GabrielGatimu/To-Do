package com.example.to_do.presentation.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do.data.Todo
import com.example.to_do.domain.repository.TodoRepository
import com.example.to_do.presentation.uiEvents.UiEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    private var todo by mutableStateOf<Todo?>(null)
    var description by mutableStateOf("")
    var title by mutableStateOf("")

    private val _uiEvent = MutableSharedFlow<UiEvents>()
    val uiEvent: MutableSharedFlow<UiEvents> = _uiEvent

    init {
        val todoId = savedStateHandle.get<Int>("todoId")!!
        if (todoId != -1){
            viewModelScope.launch {
                repository.getTodoById(todoId)?.let { todo ->
                    title = todo.title
                    description = todo.description?:""
                    this@AddEditViewModel.todo = todo
                }
            }
        }
    }
    fun onEvent(addEditScreenEvents: AddEditScreenEvents){
        when(addEditScreenEvents){

            is AddEditScreenEvents.OnDescriptionChanged -> {
                description = addEditScreenEvents.description
            }

            is AddEditScreenEvents.OnSubmit -> {
                    viewModelScope.launch {
                        repository.insertTodo(
                            Todo(
                            title = title,
                            description = description,
                            isComplete = todo?.isComplete ?: false,
                            id = todo?.id
                            )
                        )

                        _uiEvent.emit(UiEvents.OnShowToast("todo added"))
                        _uiEvent.emit(UiEvents.OnPopBackStack)
                    }
            }

            is AddEditScreenEvents.OnTitleChanged -> {
                title = addEditScreenEvents.title
            }
        }
    }
}