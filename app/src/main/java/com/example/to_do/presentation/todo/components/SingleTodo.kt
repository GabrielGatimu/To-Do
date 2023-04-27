package com.example.to_do.presentation.todo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.to_do.data.Todo
import com.example.to_do.presentation.todo.TodoScreenEvents
import com.example.to_do.presentation.todo.TodoViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun SingleTodo(
    todo: Todo,
    onEvent: (TodoScreenEvents) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel
) {
//    val delete = SwipeAction(
//        onSwipe = {
//            viewModel.onEvent(TodoScreenEvents.OnDeleteTodo(todo))
//        }
 //   )
    val delete = SwipeAction(
        onSwipe = {
            viewModel.onEvent(TodoScreenEvents.OnDeleteTodo(todo))
        },
        icon = {
               Icon(
                   imageVector = Icons.Default.Delete,
                   contentDescription = "delete",
                    tint = Color.White
               )
        },
        background = MaterialTheme.colorScheme.primary
    )

    SwipeableActionsBox (
        swipeThreshold = 100.dp,
        startActions = listOf(delete),
        endActions = listOf(delete),
        backgroundUntilSwipeThreshold = Color.White
            ){
        Surface() {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
            ) {
                Column (
                    modifier = Modifier.weight(1f)
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = todo.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Checkbox(
                    checked = todo.isComplete,
                    onCheckedChange = {isChecked ->
                        onEvent(TodoScreenEvents.OnIsCompleteChange(todo, isChecked))
                    })
            }
        }
    }

}