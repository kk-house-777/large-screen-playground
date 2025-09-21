package example.large.screen.playground.feature.maincontent

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.core.ui.AppSupporting
import example.large.screen.playground.core.ui.MainPaneContent
import example.large.screen.playground.core.ui.SubPaneContent

@Composable
fun MainContentScreen(itemId: String, navController: NavController) {
    MainPaneContent(
        itemId = itemId,
        onOpenSub = { id -> navController.navigate(AppRoute.SubContent(id)) }
    )
}

@Composable
fun MainSubContentScreen(itemId: String, navController: NavController) {
    AppSupporting<String>(
        mainPane = {
            MainPaneContent(
                itemId = itemId,
                onOpenSub = { id -> navController.navigate(AppRoute.SubContent(id)) }
            )
        },
        supportingPane = {
            SubPaneContent(parentId = itemId)
        }
    )
}

