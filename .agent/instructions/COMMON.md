# 共通実施ルール（必読）

## 守ること
- ワークフロー定義: `.agent/workflows/adaptive-android-v1.yaml`
- 依存未解決のタスクは実行禁止（`depends_on` を厳守）
- **1タスク = 1コミット = ビルド成功（:app:assembleDebug もしくは task固有のbuildコマンド）**
- コミットメッセージは YAML の `commit` をそのまま使う
- 変更は `module_scope` の範囲に収める（越境しない）

## 進め方
1. YAML を読み、未完了かつ `depends_on` 済みのタスクを選定
2. ブランチ作成: `feat/<milestone>/<task-id>`
3. `actions` を上から順に実行
4. `verify` を全て充足（最低限ビルド成功）
5. コミット → PR 作成（PR テンプレに沿ってチェック）

## PR チェックリスト（簡易）
- [ ] actions を順に実施
- [ ] ビルド成功（例: `./gradlew :app:assembleDebug`）
- [ ] 変更は `module_scope` 内に限定
- [ ] コミットメッセージは YAML の `commit` と一致