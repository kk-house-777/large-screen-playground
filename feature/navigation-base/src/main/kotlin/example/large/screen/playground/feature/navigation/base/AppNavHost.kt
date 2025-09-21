package example.large.screen.playground.feature.navigation.base

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import example.large.screen.playground.core.config.AdaptiveConfig
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.feature.detail.DetailScreen
import example.large.screen.playground.feature.home.HomeScreen
import example.large.screen.playground.feature.list.ListDetailScreen
import example.large.screen.playground.feature.list.ListScreen
import example.large.screen.playground.feature.maincontent.MainContentScreen
import example.large.screen.playground.feature.setting.SettingScreen
import example.large.screen.playground.feature.subcontent.SubContentScreen

/**
 * NavHost for the application
 * This is the shared navigation graph used by both adaptive and non-adaptive navigation
 */
@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    adaptiveConfig: AdaptiveConfig = AdaptiveConfig(),
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.Home,
        modifier = modifier
    ) {
        composable<AppRoute.Home> {
            HomeScreen(navController)
        }
        composable<AppRoute.List> {
            if (adaptiveConfig.useAdaptiveLayouts.value) {
                ListDetailScreen(navController)
            } else {
                ListScreen(navController)
            }
        }
        composable<AppRoute.Setting> {
            SettingScreen()
        }
        composable<AppRoute.Detail> {
            val detail = it.toRoute<AppRoute.Detail>()
            DetailScreen(detail.id, navController)
        }
        composable<AppRoute.MainContent> {
            val mainContent = it.toRoute<AppRoute.MainContent>()
            MainContentScreen(mainContent.id, navController)
        }
        composable<AppRoute.SubContent> {
            val subContent = it.toRoute<AppRoute.SubContent>()
            SubContentScreen(subContent.id)
        }
    }
}

/**
 * Data class for top-level route UI information
 */
data class TopLevelRoute(
    val route: AppRoute,
    val label: String,
    val icon: ImageVector
)