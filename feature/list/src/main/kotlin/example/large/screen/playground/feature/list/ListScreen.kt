package example.large.screen.playground.feature.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AdaptStrategy
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldAdaptStrategies
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldRole
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.core.ui.DetailPaneContent
import example.large.screen.playground.core.ui.ListItem
import example.large.screen.playground.core.ui.ListPaneContent
import kotlinx.coroutines.launch

val items = listOf(
    ListItem("1", "Item 1", "Description for item 1"),
    ListItem("2","Item 2","Description for item 2"),
    ListItem("3","Item 3","Description for item 3"),
    ListItem("4","Item 4","Description for item 4"),
    ListItem("5","Item 5","Description for item 5"),
)

@Composable
fun ListScreen(navController: NavController) {
    ListPaneContent(
        items = items,
        onItemClick = { id -> navController.navigate(AppRoute.Detail(id)) }
    )
}

@ExperimentalMaterial3AdaptiveApi
@Composable
fun ListDetailScreen(navController: NavController) {
    val navigator = rememberListDetailPaneScaffoldNavigator<String>()
    val scope = rememberCoroutineScope()
//    val directive = calculatePaneScaffoldDirective(currentWindowAdaptiveInfo())
//    val navigator = rememberListDetailPaneScaffoldNavigator<String>(
//        scaffoldDirective = directive,
//        adaptStrategies = ThreePaneScaffoldAdaptStrategies(
//            primaryPaneAdaptStrategy = AdaptStrategy.Hide,
//            secondaryPaneAdaptStrategy = AdaptStrategy.Reflow(ThreePaneScaffoldRole.Primary), // 出せない時はPrimaryへ合流
//            tertiaryPaneAdaptStrategy  = AdaptStrategy.Hide
//        )
//    )

    NavigableListDetailPaneScaffold(
        navigator = navigator,
        listPane = {
            AnimatedPane {
                ListPaneContent(
                    items = items,
                    onItemClick = { id ->
                        scope.launch {
                            navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, id)
                        }
                    }
                )
            }
        },
        detailPane = {
            AnimatedPane {
                val id = navigator.currentDestination?.contentKey
                if (id != null) {
                    DetailPaneContent(
                        itemId = id,
                        onOpenMain = { mainId -> navController.navigate(AppRoute.MainContent(mainId)) }
                    )
                } else {
                    // placeholder
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No item selected")
                    }
                }
            }
        },
        extraPane = {
            Text("Text")
        }
    )
}