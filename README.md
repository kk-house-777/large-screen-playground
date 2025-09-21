# Adaptive Layouts Playground (Android)

## Goal
- Top階層=Home/List/Setting を NavigationSuite（bar/rail 自動）
- List配下=Detail/MainContent を ListDetail（単一/二分割 自動）
- MainContent配下=SubContent を Supporting（主役+補助 自動）

## Modules
- :core:route          … ルート定義（depth 付き、純 Kotlin）
- :core:ui             … Adaptive 薄ラッパ（AppNavSuite / AppListDetail / AppSupporting）
- :feature:navigation  … NavHost/Top（navigation-compose 依存はここだけ）
- :feature:home
- :feature:list        … List → Detail/Main → Sub の階層
- :feature:setting
- :app                 … エントリ

## Build
- `./gradlew :app:assembleDebug`

## YAML ワークフロー（**重要**）
- **場所**: `.agent/workflows/adaptive-android-v1.yaml`
- **目的**: AI Agent による“細かな実装タスク”の自動実行計画
- **原則**:
    - 各タスクは **単独でビルド成功（:app:assembleDebug）** までを含む
    - **1 タスク = 1 コミット**（原則 fixup 禁止）
    - 依存関係が未解決のタスクは実行しない（YAML の `depends_on` を尊重）
- **実行手順（Agent 向け）**:
    1. `select next task`（未完了・依存解決済み・優先度順）
    2. `git checkout -b feat/<milestone>/<task-id>`
    3. YAML の `actions` を順に実施
    4. `./gradlew :app:assembleDebug` が成功するまで修正
    5. `git commit -m "<commit message from task>"`
    6. PR作成。`open PR`（PR テンプレ参照）

## CI Gate
- PR では必ず `./gradlew :app:assembleDebug` を実行
- YAML の形式検証（簡易）：`yamllint`（任意）