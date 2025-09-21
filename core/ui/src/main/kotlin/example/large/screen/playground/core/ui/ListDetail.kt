package example.large.screen.playground.core.ui

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ListItem(
    val id: String,
    val title: String,
    val description: String
)

@Composable
fun ListPaneContent(
    items: List<ListItem>,
    onItemClick: (String) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "List Screen",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(items, key = { it.id }) { item ->
            Card(
                modifier = Modifier.fillMaxWidth().clickable { onItemClick(item.id) }
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text(item.title, style = MaterialTheme.typography.titleMedium)
                    Text(
                        item.description,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun DetailPaneContent(
    itemId: String,
    onOpenMain: (String) -> Unit,
) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Detail Screen", style = MaterialTheme.typography.headlineMedium)
        Text("Selected Item: $itemId", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 8.dp))
        Text("This is the detail view for the selected item", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 8.dp, bottom = 24.dp))
        Button(onClick = { onOpenMain(itemId) }, modifier = Modifier.fillMaxWidth()) {
            Text("Open Main Content")
        }
    }
}

@Composable
fun MainPaneContent(
    itemId: String,
    onOpenSub: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Main Content",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Content ID: $itemId",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "This is the main content view with detailed information and rich features",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
        )
        Button(
            onClick = { onOpenSub(itemId) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Navigate to Sub Content")
        }
    }
}

@Composable
fun SubPaneContent(
    parentId: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Sub Content Screen",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Parent ID: $parentId",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "This is the third-level sub content screen, accessible from MainContent.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}