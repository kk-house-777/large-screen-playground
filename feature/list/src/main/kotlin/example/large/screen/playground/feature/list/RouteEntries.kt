package example.large.screen.playground.feature.list

import androidx.compose.runtime.Composable

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