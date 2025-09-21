package example.large.screen.playground.feature.detail

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.core.ui.DetailPaneContent

// 既存Nav向けアダプタ（今の振る舞い維持）
@Composable
fun DetailScreen(itemId: String, navController: NavController) {
    DetailPaneContent(
        itemId = itemId,
        onOpenMain = { id -> navController.navigate(AppRoute.MainContent(id)) }
    )
}
