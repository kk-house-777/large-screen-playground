package example.large.screen.playground.feature.navigation.adaptive

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import example.large.screen.playground.feature.navigation.base.AppNavHost
import example.large.screen.playground.feature.navigation.base.TopLevelRoute

/**
 * Adaptive navigation implementation using NavigationSuiteScaffold
 * Automatically adapts between bottom navigation bar, navigation rail, and navigation drawer
 * based on the screen size
 */
@Composable
fun AdaptiveNavigation(
    topLevelRoutes: List<TopLevelRoute>,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isTopLevelRoute = topLevelRoutes.any { topRoute ->
        currentDestination?.hierarchy?.any { it.route?.contains(topRoute.route::class.simpleName.orEmpty()) == true } == true
    }

    val customType = with(adaptiveInfo) {
        if (isTopLevelRoute) {
            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(this)
        } else {
            NavigationSuiteType.None
        }

        // androidx.compose.material3.adaptive:adaptive version 1.2.0-beta and later
//        if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
//            NavigationSuiteType.NavigationDrawer
//        } else {
//            NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(adaptiveInfo)
//        }
    }

    Scaffold { innerPadding ->
        NavigationSuiteScaffold(
            layoutType = customType,
            navigationSuiteItems = {
                topLevelRoutes.forEach { topRoute ->
                    val selected = currentDestination?.hierarchy?.any {
                        it.route?.contains(topRoute.route::class.simpleName.orEmpty()) == true
                    } == true

                    item(
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
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            AppNavHost(
                navController = navController,
                modifier = Modifier
            )
        }
    }
}