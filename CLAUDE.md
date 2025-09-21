# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is an Android Adaptive Layouts Playground demonstrating responsive design patterns across different screen sizes. The project implements a three-tier navigation hierarchy:

- **Top Level**: Home/List/Setting screens using NavigationSuite (automatically switches between bar/rail)
- **Second Level**: List → Detail/MainContent using ListDetail (automatically switches between single/dual pane)
- **Third Level**: MainContent → SubContent using Supporting (automatically switches between main/auxiliary)

## Architecture

### Planned Module Structure
Based on the workflow plan in `.agent/workflows/adaptive-android-v1.yaml`:

- `:core:route` - Route definitions with depth (pure Kotlin, no Android dependencies)
- `:core:ui` - Adaptive layout wrappers (AppNavSuite, AppListDetail, AppSupporting)
- `:feature:navigation` - NavHost and top-level navigation (only module with navigation-compose dependency)
- `:feature:home` - Home screen implementation
- `:feature:list` - List → Detail/Main → Sub hierarchy screens
- `:feature:setting` - Settings screen implementation
- `:app` - Application entry point

### Current State
Currently only `:app` module exists with basic MainActivity setup. Other modules will be created following the workflow plan.

## Development Commands

### Build Commands
```bash
# Build debug APK (primary verification command)
./gradlew :app:assembleDebug

# Build all variants
./gradlew app:assemble

# Clean build
./gradlew clean
```

### Testing Commands
```bash
# Run unit tests
./gradlew app:test

# Run debug unit tests specifically
./gradlew app:testDebugUnitTest

# Run instrumentation tests (requires device/emulator)
./gradlew app:connectedCheck
```

### Code Quality Commands
```bash
# Run lint
./gradlew app:lint

# Run lint and apply fixes
./gradlew app:lintFix

# Run all checks (lint + tests)
./gradlew app:check
```

### Installation Commands
```bash
# Install debug build
./gradlew app:installDebug

# Uninstall all builds
./gradlew app:uninstallAll
```

## Development Workflow

### AI Agent Workflow (YAML-Driven)
This project uses a structured AI agent workflow defined in `.agent/workflows/adaptive-android-v1.yaml`:

**Core Principles:**
- Each task must compile (`./gradlew :app:assembleDebug` must succeed)
- One commit per task (no fixup commits)
- Keep adaptive layout usage behind `:core:ui` wrappers

**Task Execution Pattern:**
1. Select next task (incomplete, dependencies resolved, priority order)
2. Create feature branch: `feat/<milestone>/<task-id>`
3. Execute YAML actions sequentially
4. Verify with `./gradlew :app:assembleDebug`
5. Commit with exact message from YAML
6. Open PR using template in `.github/PULL_REQUEST_TEMPLATE.md`

### Key Implementation Notes
- **Adaptive Dependencies**: Material3 adaptive, adaptive-layout, adaptive-navigation libraries
- **Navigation Pattern**: Only `:feature:navigation` should depend on navigation-compose
- **Module Isolation**: `:core:route` must remain pure Kotlin with no Android dependencies
- **Verification**: Every change must compile successfully before committing

## Configuration Files
- `build.gradle.kts` - Root project configuration
- `app/build.gradle.kts` - App module configuration with Compose setup
- `settings.gradle.kts` - Project module definitions
- `.agent/workflows/adaptive-android-v1.yaml` - AI agent task definitions
- `.github/PULL_REQUEST_TEMPLATE.md` - PR template with task checklist

## Current Dependencies
- Kotlin 1.9+ with Compose
- Material3 with Compose BOM
- Target/Compile SDK: 36, Min SDK: 24
- Java 11 compatibility