package example.large.screen.playground.feature.list

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute

/**
 * Entry point composables for navigation destinations.
 * These functions bridge between the navigation system and the actual screen implementations.
 */

@Composable
fun DetailRoute(id: String) {
    DetailScreen(itemId = id)
}

@Composable
fun MainContentRoute(id: String) {
    MainContentScreen(itemId = id)
}

@Composable
fun SubContentRoute(id: String) {
    SubContentScreen(parentId = id)
}

// Navigation-aware versions for screens that need to navigate
@Composable
fun DetailRouteWithNav(id: String, navController: NavController) {
    DetailScreenWithNav(itemId = id, navController = navController)
}

@Composable
fun MainContentRouteWithNav(id: String, navController: NavController) {
    MainContentScreenWithNav(itemId = id, navController = navController)
}