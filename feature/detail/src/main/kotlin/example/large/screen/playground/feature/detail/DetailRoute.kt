package example.large.screen.playground.feature.detail

import androidx.compose.runtime.Composable

/**
 * Entry point composable for Detail navigation destination.
 */
@Composable
fun DetailRoute(id: String) {
    DetailScreen(itemId = id)
}