package com.example.to_do.presentation.add_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.example.to_do.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
){
    var description by mutableStateOf("")
    var title by mutableStateOf("")

    fun onEvent(addEditScreenEvents: AddEditScreenEvents){
        when(addEditScreenEvents){

            is AddEditScreenEvents.OnDescriptionChanged -> {
                description = addEditScreenEvents.description
            }

            is AddEditScreenEvents.OnSubmit -> {

            }

            is AddEditScreenEvents.OnTitleChanged -> {
                title = addEditScreenEvents.title
            }
        }
    }
}