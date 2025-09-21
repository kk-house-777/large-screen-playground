package example.large.screen.playground.core.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Thin wrapper around Material3 ListDetailPaneScaffold for adaptive list-detail layout.
 * Automatically switches between single pane (compact) and dual pane (expanded) layouts.
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> AppListDetail(
    listPane: @Composable () -> Unit,
    detailPane: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigator: ThreePaneScaffoldNavigator<T>  = rememberListDetailPaneScaffoldNavigator<T>()
) {

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = { listPane() },
        detailPane = { detailPane() },
        modifier = modifier
    )
}

/**
 * Helper function to get ListDetailPaneScaffoldNavigator for external use
 */
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun <T> rememberAppListDetailNavigator() = rememberListDetailPaneScaffoldNavigator<T>()