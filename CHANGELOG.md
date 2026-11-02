# Changelog

All notable changes to SpitPlugin are documented here.

## v2.1.0 — 命令可配置

新增 `config.yml`，命令名、别名、权限、描述、用法和提示文本都可以自己改。

### 新增

- `config.yml`（首次启动自动生成，带中文注释）：
  - `command.name`：主命令名，改成 `tukou` 后就是 `/tukou`；
  - `command.aliases`：命令别名，支持中文，例如 `/吐口水`；
  - `command.permission`：权限节点，留空表示不检查权限；
  - `command.description` / `command.usage`：`/help` 里显示的描述与用法（`%command%` 占位符）；
  - `messages.players-only` / `messages.no-permission`：提示文本，支持 `&` 颜色代码。
- 命令改为按配置动态注册（`CommandMap`），因此命令名不再写死在 `plugin.yml` 里。
- 权限节点按 `command.permission` 自动注册，默认所有玩家可用，改成自定义节点也无需额外配置。

### 健壮性

- 命令名带空格等非法配置会输出警告并回退到 `spit`，不会导致命令消失。
- 别名里多余的斜杠会自动去掉，与主命令重名的别名会被忽略。
- 命令名与其他插件冲突时自动改用 `spitplugin:<命令名>` 并给出警告。

### 验证

在 Paper 26.3 build 237 上实测：默认配置 `/spit`、`/spitout` 正常；改成 `name: tukou` + 中文别名后 `/tukou`、`/吐口水` 正常且 `/spit` 已失效；非法命令名会回退并告警。

### English

- New `config.yml`: the command name, aliases (Chinese supported), permission node, description, usage and both messages are configurable.
- The command is now registered dynamically through Bukkit's `CommandMap` instead of being hard-coded in `plugin.yml`.
- Invalid configuration falls back to a safe default with a warning; colliding command names are namespaced as `spitplugin:<name>`.

## v2.0.0 — 汉化，只保留默认羊驼口水

**破坏性变更**：`/spitmenu` 命令与喷吐类型选择菜单已被移除。

### 变更

- 删除 `/spitmenu` 命令、喷吐类型选择菜单，以及 DUST（灰尘）、HONEY（蜂蜜）、SLIME（史莱姆）三种自定义喷吐效果。
- 只保留最初的默认行为：朝准星方向发射一只原版羊驼口水（`LLAMA_SPIT`），命中造成 1 点伤害并播放音效。
- 插件内玩家可见文本全部汉化：无权限提示、非玩家执行提示、命令与权限描述（`plugin.yml`）。
- 控制台启停日志改为中文；`/spit` 命令缺失时给出中文警告而不是空指针。
- 移除 `SpitType`、`SpitSelectionService`、`SpitMenu`、`SpitMenuListener`、`SpitMenuCommand` 五个类。
- 版本号 1.1.0 → 2.0.0。

### English

- Removed the `/spitmenu` command, the spit-type selection menu and the DUST / HONEY / SLIME custom effects.
- Only the original behaviour remains: launch a vanilla llama spit towards the crosshair, dealing 1 damage and playing the llama spit sound.
- All player-facing texts are now Simplified Chinese.

## v1.1.0 — Minecraft 26.3

Ported to Minecraft 26.3 ("Wilderness Bound") for Spigot/Paper.

### Changed

- Now compiles against `org.spigotmc:spigot-api:26.3-R0.1-SNAPSHOT`, and `plugin.yml` declares `api-version: '26.3'` (was `1.20`).
- Replaced Bukkit constants that no longer exist on 26.3:
  - `Particle.SMOKE_NORMAL` → `Particle.SMOKE`
  - `Particle.ITEM_CRACK` → `Particle.ITEM`
  - `Particle.REDSTONE` → `Particle.DUST`
  - `Enchantment.DURABILITY` → `Enchantment.UNBREAKING`
- Plugin version is now `1.1.0` and is filtered from the Maven project version into `plugin.yml`.

### Build

- `maven-compiler-plugin` 3.13.0 with `--release 21`; the plugin emits Java 21 bytecode, while a 26.3 server itself requires Java 25 to run.
- Removed `maven-shade-plugin` (the plugin has no runtime dependencies) and the hard-coded `C:\Test-Server\plugins` copy step. Copying the jar to a server is now opt-in:
  `mvn package -Dserver.plugins.dir=/path/to/plugins`.
- Added a GitHub Actions workflow: builds every push/pull request and attaches the jar to the GitHub release on `v*` tags.

### Fixed

- README listed `/spit reload` and the `spit.reload` permission, neither of which existed; the docs now match the plugin.

## v1.0

- Initial release.
