package example.large.screen.playground.core.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Thin wrapper around Material3 SupportingPaneScaffold for adaptive supporting pane layout.
 * Provides main content with optional supporting content that can be shown/hidden based on screen size.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> AppSupporting(
    mainPane: @Composable () -> Unit,
    supportingPane: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    val navigator = rememberSupportingPaneScaffoldNavigator<T>()

    SupportingPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        mainPane = { mainPane() },
        supportingPane = { supportingPane() },
        modifier = modifier
    )
}

/**
 * Helper function to get SupportingPaneScaffoldNavigator for external use
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> rememberAppSupportingNavigator() = rememberSupportingPaneScaffoldNavigator<T>()