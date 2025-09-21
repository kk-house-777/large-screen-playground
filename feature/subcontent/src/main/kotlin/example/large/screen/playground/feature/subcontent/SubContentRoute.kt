package example.large.screen.playground.feature.subcontent

import androidx.compose.runtime.Composable

/**
 * Entry point composable for SubContent navigation destination.
 */
@Composable
fun SubContentRoute(id: String) {
    SubContentScreen(parentId = id)
}