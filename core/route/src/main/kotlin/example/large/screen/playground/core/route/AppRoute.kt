package example.large.screen.playground.core.route

/**
 * Application routes with navigation depth levels.
 *
 * Depth levels:
 * - 0: Top level (Home, List, Setting)
 * - 1: Second level (Detail, MainContent)
 * - 2: Third level (SubContent)
 */
sealed class AppRoute(val depth: Int) {

    // Depth 0: Top level routes
    data object Home : AppRoute(depth = 0)
    data object List : AppRoute(depth = 0)
    data object Setting : AppRoute(depth = 0)

    // Depth 1: Second level routes
    data class Detail(val id: String) : AppRoute(depth = 1)
    data class MainContent(val id: String) : AppRoute(depth = 1)

    // Depth 2: Third level routes
    data class SubContent(val id: String) : AppRoute(depth = 2)
}