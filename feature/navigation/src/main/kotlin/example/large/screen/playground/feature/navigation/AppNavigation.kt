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
import androidx.compose.material3.ScaffoldDefaults
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
import androidx.navigation.toRoute
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.feature.detail.DetailScreen
import example.large.screen.playground.feature.home.HomeScreen
import example.large.screen.playground.feature.list.ListScreen
import example.large.screen.playground.feature.maincontent.MainContentScreen
import example.large.screen.playground.feature.setting.SettingScreen
import example.large.screen.playground.feature.subcontent.SubContentScreen

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
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val topLevelRoutes = listOf(
        TopLevelRoute(AppRoute.Home, "Home", Icons.Filled.Home),
        TopLevelRoute(AppRoute.List, "List", Icons.Filled.List),
        TopLevelRoute(AppRoute.Setting, "Setting", Icons.Filled.Settings)
    )

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
        startDestination = AppRoute.Home,
        modifier = modifier
    ) {
        composable<AppRoute.Home> {
            HomeScreen()
        }
        composable<AppRoute.List> {
            ListScreen(navController)
        }
        composable<AppRoute.Setting> {
            SettingScreen()
        }
        composable<AppRoute.Detail> {
            val detail = it.toRoute<AppRoute.Detail>()
            DetailScreen(detail.id)
        }
        composable<AppRoute.MainContent> {
            val mainContent = it.toRoute<AppRoute.MainContent>()
            MainContentScreen(mainContent.id)
        }
        composable<AppRoute.SubContent> {
            val subContent = it.toRoute<AppRoute.SubContent>()
            SubContentScreen(subContent.id)
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