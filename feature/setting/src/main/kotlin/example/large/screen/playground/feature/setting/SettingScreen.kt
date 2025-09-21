package example.large.screen.playground.feature.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import example.large.screen.playground.core.config.Config

@Composable
fun SettingScreen() {
    var useAdaptiveLayouts by remember { mutableStateOf(Config.useAdaptiveLayouts) }
    var enableListDetailLayout by remember { mutableStateOf(Config.enableListDetailLayout) }
    var enableSupportingPaneLayout by remember { mutableStateOf(Config.enableSupportingPaneLayout) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Settings",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Adaptive Layout Configuration",
                    style = MaterialTheme.typography.titleMedium
                )

                SettingToggle(
                    title = "Use Adaptive Layouts",
                    description = "Enable adaptive layout components",
                    checked = useAdaptiveLayouts,
                    onCheckedChange = {
                        useAdaptiveLayouts = it
                        Config.setAdaptiveLayouts(it)
                    }
                )

                SettingToggle(
                    title = "List-Detail Layout",
                    description = "Enable list-detail adaptive layout",
                    checked = enableListDetailLayout,
                    onCheckedChange = {
                        enableListDetailLayout = it
                        Config.setListDetailLayout(it)
                    }
                )

                SettingToggle(
                    title = "Supporting Pane Layout",
                    description = "Enable supporting pane adaptive layout",
                    checked = enableSupportingPaneLayout,
                    onCheckedChange = {
                        enableSupportingPaneLayout = it
                        Config.setSupportingPaneLayout(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun SettingToggle(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}