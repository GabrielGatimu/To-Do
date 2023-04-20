package com.example.to_do.presentation.add_edit

sealed class AddEditScreenEvents {
    data class OnDescriptionChanged(val description: String): AddEditScreenEvents()

    data class OnTitleChanged(val title: String): AddEditScreenEvents()

    object OnSubmit: AddEditScreenEvents()
}