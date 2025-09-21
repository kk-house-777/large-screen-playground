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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.feature.detail.DetailRoute
import example.large.screen.playground.feature.detail.DetailScreen
import example.large.screen.playground.feature.home.HomeScreen
import example.large.screen.playground.feature.list.ListScreen
import example.large.screen.playground.feature.maincontent.MainContentRoute
import example.large.screen.playground.feature.maincontent.MainContentScreen
import example.large.screen.playground.feature.setting.SettingScreen
import example.large.screen.playground.feature.subcontent.SubContentRoute
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
            val selected = currentDestination?.hierarchy?.any { it.route == topRoute.routeName } == true

            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(topRoute.routeName) {
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
        startDestination = HOME_ROUTE,
        modifier = modifier
    ) {
        composable(HOME_ROUTE) {
            HomeScreen()
        }
        composable(LIST_ROUTE) {
            ListScreen()
        }
        composable(SETTING_ROUTE) {
            SettingScreen()
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")!!
            DetailScreen(id)
        }
        composable(
            route = "main/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")!!
            MainContentScreen(id)
        }
        composable(
            route = "sub/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            val id = it.arguments?.getString("id")!!
            SubContentScreen(id)
        }
    }
}


/**
 * Route string constants
 */
private const val HOME_ROUTE = "home"
private const val LIST_ROUTE = "list"
private const val SETTING_ROUTE = "setting"

/**
 * Extension property to get route name for navigation
 */
private val AppRoute.routeName: String
    get() = when (this) {
        is AppRoute.Home -> HOME_ROUTE
        is AppRoute.List -> LIST_ROUTE
        is AppRoute.Setting -> SETTING_ROUTE
        is AppRoute.Detail -> "detail/${id}"
        is AppRoute.MainContent -> "main/${id}"
        is AppRoute.SubContent -> "sub/${id}"
    }

/**
 * Data class for top-level route UI information
 */
private data class TopLevelRoute(
    val route: AppRoute,
    val label: String,
    val icon: ImageVector
) {
    val routeName: String
        get() = route.routeName
}