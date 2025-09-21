package example.large.screen.playground.core.ui

import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// Re-export NavigationSuiteScope for external modules
typealias AppNavigationSuiteScope = NavigationSuiteScope

/**
 * Thin wrapper around Material3 NavigationSuiteScaffold for adaptive navigation.
 * Automatically switches between navigation bar (compact) and rail (expanded) layouts.
 */
@Composable
fun AppNavSuite(
    navigationSuiteItems: AppNavigationSuiteScope.() -> Unit,
    modifier: Modifier = Modifier,
    layoutType: NavigationSuiteType = NavigationSuiteType.NavigationBar,
    content: @Composable () -> Unit
) {
    NavigationSuiteScaffold(
        navigationSuiteItems = navigationSuiteItems,
        layoutType = layoutType,
        modifier = modifier,
        content = content
    )
}