# Changelog

All notable changes to SpitPlugin are documented here.

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
