package example.large.screen.playground.core.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class AdaptiveConfig(
    val useAdaptiveLayouts: MutableState<Boolean> = mutableStateOf(false)
)

val LocalAdaptiveConfig = compositionLocalOf<AdaptiveConfig> {
    error("AdaptiveConfig not provided")
}

@Composable
fun rememberAdaptiveConfig(
    initialUseAdaptiveLayouts: Boolean = false
): AdaptiveConfig {
    return remember {
        AdaptiveConfig(
            useAdaptiveLayouts = mutableStateOf(initialUseAdaptiveLayouts)
        )
    }
}

/**
 * Helper function to get the current adaptive layout flag value
 */
@Composable
fun useAdaptiveLayouts(): Boolean {
    return LocalAdaptiveConfig.current.useAdaptiveLayouts.value
}

/**
 * Helper function to update the adaptive layout flag
 */
@Composable
fun updateAdaptiveLayouts(enabled: Boolean) {
    LocalAdaptiveConfig.current.useAdaptiveLayouts.value = enabled
}