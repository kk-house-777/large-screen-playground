サンプルは以下。型安全はプロジェクトで使用しているroute通りにするなどの適切な改良が必要。

```kotlin
NavigationSuiteScaffold(
    navigationSuiteItems = {
        item(
            selected = (current == "home"),
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, null) },
            label = { Text("Home") }
        )
        item(
            selected = (current == "list"),
            onClick = { navController.navigate("list") },
            icon = { Icon(Icons.Filled.List, null) },
            label = { Text("List") }
        )
        item(
            selected = (current == "setting"),
            onClick = { navController.navigate("setting") },
            icon = { Icon(Icons.Filled.Settings, null) },
            label = { Text("Setting") }
        )
    }
) { innerPadding ->
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.padding(innerPadding)
    ) {
        composable("home") { HomeScreen() }
        composable("list") { ListScreen() }
        composable("setting") { SettingScreen() }
    }
}
```