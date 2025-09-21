package example.large.screen.playground.core.route

import kotlinx.serialization.Serializable

@Serializable
sealed class AppRoute {

    @Serializable
    data object Home : AppRoute()

    @Serializable
    data object List : AppRoute()

    @Serializable
    data object Setting : AppRoute()

    @Serializable
    data class Detail(val id: String) : AppRoute()

    @Serializable
    data class MainContent(val id: String) : AppRoute()

    @Serializable
    data class SubContent(val id: String) : AppRoute()
}