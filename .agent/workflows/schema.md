# ファイル: .agent/workflows/adaptive-android-v1.yaml
workflow: "Adaptive layouts playground (Android v1)"
principles:
- "Each task must compile (:app:assembleDebug)."
- "One commit per task."
- "Keep adaptive usage behind :core:ui wrappers."

milestones:
- name: "<短い名前>"
  description: "<目的説明>"
  tasks:
    - id: "<一意ID: kebab-case>"
      goal: "<このタスクの到達点>"
      module_scope: ["<影響モジュール例: :core:route, :feature:list, :app>"]
      depends_on: ["<先行タスクID>", "..."]   # 省略可
      actions:
        - "<手順1>"
        - "<手順2>"
          verify:
        - "Run ./gradlew :app:assembleDebug and it succeeds"
        - "<任意の手動確認項目>"
          commit: "<必須: コミットメッセージ>"
          notes: "<補足: リスク/代替案など 任意>"