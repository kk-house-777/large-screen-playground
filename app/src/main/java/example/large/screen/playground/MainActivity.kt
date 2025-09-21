package example.large.screen.playground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import example.large.screen.playground.core.config.LocalAdaptiveConfig
import example.large.screen.playground.core.config.rememberAdaptiveConfig
import example.large.screen.playground.feature.navigation.AppNavigation
import example.large.screen.playground.feature.navigation.adaptive.AdaptiveNavigation
import example.large.screen.playground.feature.navigation.notadaptive.NotAdaptiveNavigation
import example.large.screen.playground.ui.theme.LargescreenplaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}

@Composable
fun AppRoot() {
    val adaptiveConfig = rememberAdaptiveConfig(
        initialUseAdaptiveLayouts = false
    )

    LargescreenplaygroundTheme {
        CompositionLocalProvider(
            LocalAdaptiveConfig provides adaptiveConfig
        ) {
            // Switch between adaptive and non-adaptive navigation based on config flag
            if (adaptiveConfig.useAdaptiveLayouts.value) {
                AdaptiveNavigation()
            } else {
                NotAdaptiveNavigation()
            }
        }
    }
}