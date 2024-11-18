# To-Do List App

A simple Android app built with **Jetpack Compose** for managing and keeping track of tasks. The app features a modern and responsive user interface.

## 📱 Features
- **Add New Task**: Add tasks you want to complete.
- **Mark Tasks as Done**: Check off tasks that are completed.
- **Input Field Reset**: Automatically clears the input field after adding a task.
- **Responsive Layout**: Layout adjusts to the screen size of the device.

## 🛠️ Technologies Used
- **Kotlin**: The primary programming language.
- **Jetpack Compose**: A declarative UI toolkit for Android.
- **Material 3**: A modern component design using Material Design principles.

## 🖥️ Project Structure
.
├── MainActivity.kt          # Entry point of the application
├── TodoPage.kt              # The main page displaying the task list
├── TodoItemView.kt          # A composable for each individual task
├── TodoItem.kt              # Data model for a task
└── themes
    └── Theme.kt             # App theme configuration
.

## 📂 Prerequisites

- **Android Studio**: The latest version (recommended Android Studio Ladybug or higher).
- **JDK 11 or higher**.
- A physical or emulator device with Android API Level 24 or higher.

## 🚀 How to Run

1. Clone the repository:
```bash
git clone https://github.com/username/todo-list-app.git
cd todo-list-app

2. Open the project in Android Studio:
   - Choose File > _Open..._ and select the project folder.
3. Sync Gradle:
   - Click ==Sync Project with Gradle Files== in the Android Studio toolbar.
4. Run the App:
   - Choose a device or emulator.
   - Click the Run button (green triangle icon) in the toolbar.

## 📄 Core Code

## TodoItem Model
```kotlin
data class TodoItem(
    val id: Int,
    val task: String,
    val isDone: Boolean = false
)


## TodoPage Composable
```kotlin
@Composable
fun TodoPage(modifier: Modifier = Modifier) {
    var todoList by remember { mutableStateOf(listOf<TodoItem>()) }
    var newTaskText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Input & Add Button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = newTaskText,
                onValueChange = { newTaskText = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Add new task...") }
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
                Text("Add")
            }
        }

        // Task List
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


## TodoItemView Composable
```kotlin
@Composable
fun TodoItemView(todo: TodoItem, onCheckedChange: () -> Unit) {
    Row(
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


