package example.large.screen.playground.feature.list

import androidx.compose.runtime.Composable
import example.large.screen.playground.feature.detail.DetailScreen
import example.large.screen.playground.feature.maincontent.MainContentScreen
import example.large.screen.playground.feature.subcontent.SubContentScreen

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