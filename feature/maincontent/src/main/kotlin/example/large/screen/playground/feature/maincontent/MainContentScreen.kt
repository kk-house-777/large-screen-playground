package example.large.screen.playground.feature.maincontent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.AdaptStrategy
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.SupportingPaneScaffoldRole
import androidx.compose.material3.adaptive.layout.ThreePaneScaffoldAdaptStrategies
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation.NavigableSupportingPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberSupportingPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import example.large.screen.playground.core.route.AppRoute
import example.large.screen.playground.core.ui.MainPaneContent
import example.large.screen.playground.core.ui.SubPaneContent
import kotlinx.coroutines.launch

@Composable
fun MainContentScreen(itemId: String, navController: NavController) {
    MainPaneContent(
        itemId = itemId,
        onOpenSub = { id -> navController.navigate(AppRoute.SubContent(id)) }
    )
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainSubContentScreen(itemId: String, navController: NavController) {
//    val navigator = rememberSupportingPaneScaffoldNavigator<String>()
    val scope = rememberCoroutineScope()

    val directive = calculatePaneScaffoldDirective(currentWindowAdaptiveInfo())
    val navigator = rememberSupportingPaneScaffoldNavigator<String>(
        scaffoldDirective = directive,
        adaptStrategies = ThreePaneScaffoldAdaptStrategies(
            // main
            primaryPaneAdaptStrategy = AdaptStrategy.Hide,
            // supporting
            secondaryPaneAdaptStrategy = AdaptStrategy.Hide,
            // extra
            tertiaryPaneAdaptStrategy = AdaptStrategy.Hide
        )
    )

    NavigableSupportingPaneScaffold(
        navigator = navigator,
        mainPane = {
            AnimatedPane {
                val hasSupporting = navigator.currentDestination?.contentKey != null
                MainPaneContent(
                    itemId = itemId,
                    onOpenSub = { id ->
                        // paneの数でナビゲートを分岐する
                        if (directive.maxHorizontalPartitions == 1) {
                            navController.navigate(AppRoute.SubContent(id = id))
                        } else {
                            scope.launch {
                                navigator.navigateTo(SupportingPaneScaffoldRole.Supporting, id)
                            }
                        }

                    },
                    showSubButton = !hasSupporting // Hide button when SubContent is showing
                )
            }
        },
        supportingPane = {
            AnimatedPane {
                val subContentId = navigator.currentDestination?.contentKey
                if (subContentId != null) {
                    SubPaneContent(
                        parentId = subContentId,
                        onClose = {
                            scope.launch {
                                navigator.navigateBack()
                            }
                        }
                    )
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No sub content selected")
                    }
                }
            }
        }
    )
}

