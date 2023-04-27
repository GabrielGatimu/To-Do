package com.example.to_do.presentation.todo.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.to_do.presentation.todo.TodoScreenEvents
import com.example.to_do.presentation.todo.TodoViewModel
import com.example.to_do.presentation.uiEvents.UiEvents
import kotlinx.coroutines.flow.collectLatest

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    onNavigate: (UiEvents.OnNavigate) -> Unit,
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    val snackbarHostState = remember {SnackbarHostState()}
    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collectLatest { event ->
            when(event){
                is UiEvents.OnNavigate -> {
                    onNavigate(event)
                }

                is UiEvents.OnShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar (
                        message = event.message,
                        actionLabel = event.actions
                    )
                    if (result == SnackbarResult.ActionPerformed){
                        viewModel.onEvent(TodoScreenEvents.OnUndoDeleteTodo)
                    }
                }

                else -> Unit
            }

        }
    }

    Scaffold(
        topBar = {
                 TextField(
                     value = viewModel.searchQuery.value,
                     onValueChange = {viewModel.onEvent(TodoScreenEvents.OnSearchChanged(it))},
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(16.dp)
                    ,
                     placeholder = {Text(text = "search here ..")},
                     trailingIcon = {
                         Row {
                             IconButton(onClick = { viewModel.onSearch()}) {
                                 Icon(imageVector = Icons.Rounded.Search, contentDescription = "search")
                             }
                             if (viewModel.searchQuery.value.isNotBlank()){
                                 IconButton(onClick = {
                                     viewModel.onEvent(TodoScreenEvents.OnSearchChanged(""))
                                     viewModel.getTodos()
                                 }) {
                                     Icon(imageVector = Icons.Rounded.Clear, contentDescription = "clear")
                                 }
                             }
                         }
                     }
                 )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(TodoScreenEvents.OnCreateTodoClicked) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "add")
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {

        Box{
            if (state.isLoading){
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }else{
                LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())){
                    items(state.todos){todo ->
                        SingleTodo(
                            todo = todo, 
                            viewModel = viewModel,
                            onEvent = viewModel::onEvent, 
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.onEvent((TodoScreenEvents.OnTodoClicked(todo)))
                                }
                                .padding(16.dp)
                            )
                    }
                    
                    item{
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
    }
}