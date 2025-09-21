package example.large.screen.playground.feature.list

import example.large.screen.playground.core.route.AppRoute

/**
 * Extension property to get route name for navigation.
 * This duplicates the extension from :feature:navigation to avoid circular dependency.
 */
internal val AppRoute.routeName: String
    get() = when (this) {
        is AppRoute.Home -> "home"
        is AppRoute.List -> "list"
        is AppRoute.Setting -> "setting"
        is AppRoute.Detail -> "detail/${id}"
        is AppRoute.MainContent -> "main/${id}"
        is AppRoute.SubContent -> "sub/${id}"
    }