package example.large.screen.playground.feature.maincontent

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.core.ui.MainPaneContent

@Composable
fun MainContentScreen(itemId: String, navController: NavController) {
    MainPaneContent(
        itemId = itemId,
        onOpenSub = { id -> navController.navigate(AppRoute.SubContent(id)) }
    )
}

