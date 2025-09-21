# <task-id>

参照: `.agent/instructions/COMMON.md`

## 概要
- Milestone: <milestone-name>
- Task ID: <task-id>
- Goal: <YAML の goal をそのまま>
- Module Scope: <YAML の module_scope>

## 実行
1) ブランチ: `feat/<milestone-name>/<task-id>`
2) YAML の `actions` を実行
3) YAML の `verify` を満たす（最低限ビルド成功）
4) コミット: <YAML の commit をそのまま>
5) PR 作成（テンプレに従う）