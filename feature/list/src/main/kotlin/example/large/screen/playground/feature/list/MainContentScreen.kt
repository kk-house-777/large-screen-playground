package example.large.screen.playground.feature.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.large.screen.playground.core.ui.AppSupporting
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainContentScreen(itemId: String) {
    var showSupporting by remember { mutableStateOf(false) }
    val navigator = rememberSupportingPaneScaffoldNavigator<Unit>()
    val coroutineScope = rememberCoroutineScope()

    val mainPane: @Composable () -> Unit = {
        MainContentPane(
            itemId = itemId,
            onShowSupporting = {
                showSupporting = true
                coroutineScope.launch {
                    navigator.navigateTo(SupportingPaneScaffoldRole.Supporting, Unit)
                }
            }
        )
    }

    val supportingPane: @Composable () -> Unit = {
        if (showSupporting) {
            SubContentPane(
                itemId = itemId,
                onClose = {
                    showSupporting = false
                    coroutineScope.launch {
                        navigator.navigateBack()
                    }
                }
            )
        }
    }

    AppSupporting<Unit>(
        mainPane = mainPane,
        supportingPane = supportingPane
    )
}

@Composable
private fun MainContentPane(
    itemId: String,
    onShowSupporting: () -> Unit
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
            text = "This is the main content view with detailed information",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )
        Button(
            onClick = onShowSupporting
        ) {
            Text("Show Supporting Content")
        }
    }
}

@Composable
private fun SubContentPane(
    itemId: String,
    onClose: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Supporting Content",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Sub ID: $itemId",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "This is the supporting pane that shows additional information or actions related to the main content.",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        )
        Button(
            onClick = onClose
        ) {
            Text("Close Supporting")
        }
    }
}