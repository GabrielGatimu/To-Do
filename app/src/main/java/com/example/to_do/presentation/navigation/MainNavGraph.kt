package com.example.to_do.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.to_do.presentation.add_edit.components.AddEditScreen
import com.example.to_do.presentation.screens.Screens
import com.example.to_do.presentation.todo.components.TodoScreen

@Composable
fun MainNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screens.TodoScreen.route
    ){
        composable(Screens.TodoScreen.route){
            TodoScreen(onNavigate = {navHostController.navigate(it.route)})
        }
        composable(
            route = Screens.AddEditScreen.route + "?todoId={todoId}",
            arguments = listOf(
                navArgument(name = "todoId"){
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ){
//            AddEditScreen(onPopBackStack = {navHostController.popBackStack()})
            AddEditScreen(onPopBackStack = { navHostController.popBackStack() })
        }
    }
}