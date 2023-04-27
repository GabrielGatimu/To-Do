package com.example.to_do.presentation.screens

sealed class Screens(val route: String) {
    object TodoScreen : Screens("todo_screen")

    object AddEditScreen: Screens("add_edit_screen")
}