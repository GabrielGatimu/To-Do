package com.example.to_do.presentation.uiEvents

sealed class UiEvents {
    object OnPopBackStack: UiEvents()

    data class OnNavigate(val route: String): UiEvents()

    data class OnShowSnackBar(
        val message: String,
        val actions: String? = null
    ): UiEvents()

    data class OnShowToast(val message: String): UiEvents()
}