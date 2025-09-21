package example.large.screen.playground.feature.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.large.screen.playground.core.ui.AppListDetail
import kotlinx.coroutines.launch

data class ListItem(
    val id: String,
    val title: String,
    val description: String
)

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailScreen() {
    val items = remember {
        listOf(
            ListItem("1", "Item 1", "Description for item 1"),
            ListItem("2", "Item 2", "Description for item 2"),
            ListItem("3", "Item 3", "Description for item 3"),
            ListItem("4", "Item 4", "Description for item 4"),
            ListItem("5", "Item 5", "Description for item 5")
        )
    }

    var selectedItem by remember { mutableStateOf<ListItem?>(null) }
    val navigator = rememberListDetailPaneScaffoldNavigator<ListItem>()
    val coroutineScope = rememberCoroutineScope()

    AppListDetail(
        navigator = navigator,
        listPane = {
            ListPane(
                items = items,
                onItemClick = { item ->
                    selectedItem = item
                    coroutineScope.launch {
                        navigator.navigateTo(pane = ListDetailPaneScaffoldRole.Detail, contentKey = item)
                    }
                }
            )
        },
        detailPane = {
            selectedItem?.let { item ->
                DetailPane(item = item)
            } ?: EmptyDetailPane()
        }
    )
}

@Composable
private fun ListPane(
    items: List<ListItem>,
    onItemClick: (ListItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "List Items",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(items) { item ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClick(item) }
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
                }
            }
        }
    }
}

@Composable
private fun DetailPane(item: ListItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detail View",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Additional details for ${item.title} would be shown here. This demonstrates the adaptive list-detail pattern where on larger screens this detail pane appears alongside the list, and on smaller screens it replaces the list view.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun EmptyDetailPane() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Select an item",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Choose an item from the list to see its details",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}