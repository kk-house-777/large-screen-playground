package example.large.screen.playground.feature.navigation.adaptive

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.feature.navigation.base.AppNavHost

/**
 * Adaptive navigation implementation (placeholder - will use NavigationSuiteScaffold)
 * This is a placeholder that will be replaced with NavigationSuiteScaffold implementation
 */
@Composable
fun AdaptiveNavigation(
    navController: NavHostController = rememberNavController()
) {
    // TODO: Replace with NavigationSuiteScaffold implementation
    // For now, using standard Scaffold as placeholder
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        },
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

/**
 * Bottom navigation bar for top-level destinations (placeholder)
 */
@Composable
private fun AppBottomNavigationBar(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelRoutes = listOf(
        TopLevelRoute(AppRoute.Home, "Home", Icons.Filled.Home),
        TopLevelRoute(AppRoute.List, "List", Icons.AutoMirrored.Filled.List),
        TopLevelRoute(AppRoute.Setting, "Setting", Icons.Filled.Settings)
    )

    NavigationBar {
        topLevelRoutes.forEach { topRoute ->
            val selected = currentDestination?.hierarchy?.any {
                it.route?.contains(topRoute.route::class.simpleName.orEmpty()) == true
            } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(topRoute.route) {
                        popUpTo(navController.graph.startDestinationRoute!!) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = topRoute.icon,
                        contentDescription = topRoute.label
                    )
                },
                label = { Text(topRoute.label) }
            )
        }
    }
}

/**
 * Data class for top-level route UI information
 */
private data class TopLevelRoute(
    val route: AppRoute,
    val label: String,
    val icon: ImageVector
)