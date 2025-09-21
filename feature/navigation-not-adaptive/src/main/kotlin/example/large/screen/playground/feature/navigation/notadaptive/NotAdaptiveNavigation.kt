package example.large.screen.playground.feature.navigation.notadaptive

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
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.feature.navigation.base.AppNavHost
import example.large.screen.playground.feature.navigation.base.TopLevelRoute

/**
 * Non-adaptive navigation implementation using standard Scaffold and bottom navigation
 */
@Composable
fun NotAdaptiveNavigation(
    topLevelRoutes: List<TopLevelRoute>,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController, topLevelRoutes = topLevelRoutes)
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
 * Bottom navigation bar for top-level destinations
 */
@Composable
private fun AppBottomNavigationBar(
    topLevelRoutes: List<TopLevelRoute>,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isTopLevelRoute = topLevelRoutes.any { topRoute ->
        currentDestination?.hierarchy?.any { it.route?.contains(topRoute.route::class.simpleName.orEmpty()) == true } == true
    }

    if (isTopLevelRoute) {
        NavigationBar {
            topLevelRoutes.forEach { topRoute ->
                val selected = currentDestination?.hierarchy?.any { it.route?.contains(topRoute.route::class.simpleName.orEmpty()) == true } == true

                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        navController.navigate(topRoute.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            popUpTo(navController.graph.startDestinationRoute!!) {
                                saveState = true
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
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
}