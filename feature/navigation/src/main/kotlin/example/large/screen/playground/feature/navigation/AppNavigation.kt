package example.large.screen.playground.feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

/**
 * Top-level navigation composable with bottom navigation
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(navController = navController)
        }
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
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelRoutes = listOf(
        TopLevelRoute.Home,
        TopLevelRoute.List,
        TopLevelRoute.Setting
    )

    NavigationBar {
        topLevelRoutes.forEach { route ->
            val selected = currentDestination?.hierarchy?.any { it.route == route.route } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(route.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        popUpTo(navController.graph.startDestinationRoute.orEmpty()) {
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
                        imageVector = route.icon,
                        contentDescription = route.label
                    )
                },
                label = { Text(route.label) }
            )
        }
    }
}

/**
 * NavHost for the application
 */
@Composable
private fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomePlaceholderScreen()
        }
        composable("list") {
            ListPlaceholderScreen()
        }
        composable("setting") {
            SettingPlaceholderScreen()
        }
    }
}

/**
 * Placeholder screen for Home
 */
@Composable
private fun HomePlaceholderScreen() {
    Text("Home Screen - Placeholder")
}

/**
 * Placeholder screen for List
 */
@Composable
private fun ListPlaceholderScreen() {
    Text("List Screen - Placeholder")
}

/**
 * Placeholder screen for Setting
 */
@Composable
private fun SettingPlaceholderScreen() {
    Text("Setting Screen - Placeholder")
}

/**
 * Data class for top-level routes
 */
private data class TopLevelRoute(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    companion object {
        val Home = TopLevelRoute(
            route = "home",
            label = "Home",
            icon = Icons.Filled.Home
        )
        val List = TopLevelRoute(
            route = "list",
            label = "List",
            icon = Icons.Filled.List
        )
        val Setting = TopLevelRoute(
            route = "setting",
            label = "Setting",
            icon = Icons.Filled.Settings
        )
    }
}