package example.large.screen.playground.feature.maincontent

import androidx.compose.runtime.Composable

/**
 * Entry point composable for MainContent navigation destination.
 */
@Composable
fun MainContentRoute(id: String) {
    MainContentScreen(itemId = id)
}