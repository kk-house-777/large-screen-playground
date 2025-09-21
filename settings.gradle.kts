pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "large-screen-playground"
include(":app")
include(":core:config")
include(":core:route")
include(":core:ui")
include(":feature:navigation-base")
include(":feature:navigation-adaptive")
include(":feature:navigation-not-adaptive")
include(":feature:home")
include(":feature:list")
include(":feature:setting")
include(":feature:detail")
include(":feature:maincontent")
include(":feature:subcontent")