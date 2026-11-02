# Changelog

All notable changes to SpitPlugin are documented here.

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

- `maven-compiler-plugin` 3.13.0 with `--release 21`; the plugin emits Java 21 bytecode, while a 26.3 server itself requires Java SE 25 to run.
- Removed `maven-shade-plugin` (the plugin has no runtime dependencies) and the hard-coded `C:\Test-Server\plugins` copy step. Copying the jar to a server is now opt-in:
  `mvn package -Dserver.plugins.dir=/path/to/plugins`.
- Added a GitHub Actions workflow: builds every push/pull request and attaches the jar to the GitHub release on `v*` tags.

### Fixed

- README listed `/spit reload` and the `spit.reload` permission, neither of which existed; the docs now match the plugin (`/spit`, `/spitmenu`, `spit.use`).

## v1.0

- Initial release.
