package com.yuda.to_dolistapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun TodoPage(modifier: Modifier = Modifier) {
    var todoList by remember { mutableStateOf(listOf<TodoItem>()) }
    var newTaskText by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .padding(16.dp)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Tambah tugas baru...") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (newTaskText.isNotBlank()) {
                    todoList = todoList + TodoItem(
                        id = todoList.size + 1,
                        task = newTaskText,
                    )
                    newTaskText = ""
                }
            }) {
                Text("Tambah")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(todoList) { todo ->
                TodoItemView(todo) {
                    todoList = todoList.map {
                        if (it.id == todo.id) it.copy(isDone = !it.isDone) else it
                    }
                }
            }
        }
    }
}

@Composable
fun TodoItemView(todo: TodoItem, onCheckedChange: () -> Unit) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { onCheckedChange() }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = todo.task)
    }
}