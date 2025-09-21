# Large Screen Playground (Android)

## Goal
- Multi-screen navigation flow implementation
- Type-safe navigation using Kotlin Serialization
- Feature module architecture with clean separation
- Navigation flows: Home → MainContent, List → Detail → MainContent → SubContent

## Modules
- `:core:route`          … Route definitions with Kotlin Serialization
- `:core:ui`             … Adaptive UI components (AppNavSuite / AppListDetail / AppSupporting)
- `:feature:navigation`  … NavHost and top-level navigation (navigation-compose dependency)
- `:feature:home`        … Home screen with navigation to MainContent
- `:feature:list`        … List screen with clickable items navigating to Detail
- `:feature:detail`      … Detail screen with navigation to MainContent
- `:feature:maincontent` … Main content screen with navigation to SubContent
- `:feature:subcontent`  … Sub content screen
- `:feature:setting`     … Settings screen
- `:app`                 … Application entry point

## Build
- `./gradlew :app:assembleDebug`

## Navigation Flow
1. **Home Screen** → "Open Main Content" button → MainContent
2. **List Screen** → Click list item → Detail → "Open Main Content" button → MainContent
3. **MainContent Screen** → "Navigate to Sub Content" button → SubContent
4. **Bottom Navigation**: Home, List, Settings (visible only on top-level screens)

## Features
- **Type-safe Navigation**: Using `@Serializable` route classes with Navigation Compose
- **Multi-module Architecture**: Clean separation of concerns with feature modules
- **Adaptive UI**: Conditional bottom navigation bar display
- **Navigation Flows**: Complete navigation paths between all screen levels

## Key Implementation
- **AppRoute**: Sealed class with `@Serializable` data classes for type-safe navigation
- **Bottom Navigation**: Automatically hidden on non-top-level screens
- **NavController**: Passed through feature modules for navigation actions
- **Clean Dependencies**: Each feature module only depends on necessary components

## Tags
- `pre-adaptive-baseline`: Simple navigation implementation without adaptive components