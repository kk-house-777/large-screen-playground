package example.large.screen.playground.core.route

import kotlinx.serialization.Serializable

/**
 * Type-safe navigation routes using Kotlin Serialization.
 *
 * Depth levels:
 * - 0: Top level (Home, List, Setting)
 * - 1: Second level (Detail, MainContent)
 * - 2: Third level (SubContent)
 */
@Serializable
sealed class AppRoute {

    // Depth 0: Top level routes
    @Serializable
    data object Home : AppRoute()

    @Serializable
    data object List : AppRoute()

    @Serializable
    data object Setting : AppRoute()

    // Depth 1: Second level routes
    @Serializable
    data class Detail(val id: String) : AppRoute()

    @Serializable
    data class MainContent(val id: String) : AppRoute()

    // Depth 2: Third level routes
    @Serializable
    data class SubContent(val id: String) : AppRoute()
}