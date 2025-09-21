package example.large.screen.playground.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import example.large.screen.playground.core.route.AppRoute

/**
 * List screen that uses real navigation to Detail/MainContent screens
 */
@Composable
fun ListScreenWithNavigation(
    navController: NavController
) {
    val items = listOf(
        ListItem("1", "Item 1", "Description for item 1"),
        ListItem("2", "Item 2", "Description for item 2"),
        ListItem("3", "Item 3", "Description for item 3"),
        ListItem("4", "Item 4", "Description for item 4"),
        ListItem("5", "Item 5", "Description for item 5")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "List with Navigation",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        // Navigate to Detail screen
                        navController.navigate(AppRoute.Detail(item.id).routeName)
                    }
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    // Optional: Add button to navigate to MainContent directly
                    Button(
                        onClick = {
                            navController.navigate(AppRoute.MainContent(item.id).routeName)
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Open Main Content")
                    }
                }
            }
        }
    }
}