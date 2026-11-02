# SpitPlugin 💦

**SpitPlugin** 是一个轻量的 Spigot/Paper 插件：让玩家像羊驼一样吐口水 🦙 —— 朝准星方向发射一只羊驼口水，给服务器添点恶作剧的乐趣。

> 支持版本：**Minecraft 26.3**（Spigot / Paper）。服务器需要 Java SE 25。

---

## ✨ 功能

* 💦 朝准星方向吐口水
* ⚙️ 命令名、别名、权限、提示文本都可以在 `config.yml` 里改（默认 `/spit`）
* 🔊 发射时播放羊驼吐口水的音效
* 💥 命中生物造成 1 点伤害

---

## 📥 安装

1. 下载插件 `.jar`
2. 放进服务器的 `plugins` 目录
3. 重启服务器（首次启动会生成 `plugins/SpitPlugin/config.yml`）
4. 完成 ✅

---

## 📜 命令

| 命令 | 说明 |
| --- | --- |
| `/spit` | 朝准星方向吐一口口水（命令名可在配置里改） |
| `/spitout` | 默认自带的别名，可在配置里改 |

---

## ⚙️ 配置

`plugins/SpitPlugin/config.yml`（改完重启服务器或用插件管理器重载本插件生效）：

```yaml
# 自定义指令
command:
  # 主命令名，不带斜杠。改成 tukou 后玩家就用 /tukou 吐口水
  name: spit

  # 命令别名，可写多个；不需要就写 aliases: []
  aliases:
    - spitout

  # 使用命令需要的权限节点；留空（permission: ''）表示不检查权限
  permission: spit.use

  # 命令描述，显示在 /help 里
  description: 朝准星方向吐一口口水

  # 用法提示，%command% 会替换成上面的命令名
  usage: /%command%

# 提示文本，支持 & 颜色代码
messages:
  players-only: '&e该命令只能由游戏内玩家执行。'
  no-permission: '&c你没有权限使用这个命令。'
```

几个细节：

* 命令名和别名都可以是中文，比如 `name: 吐口水`，玩家直接输入 `/吐口水` 即可；写了多余的斜杠会自动去掉。
* 命令名里带空格属于非法配置，插件会输出警告并回退到 `spit`，不会导致命令消失。
* 修改 `permission` 后无需在别处注册：插件会以「默认所有玩家可用」自动注册该权限节点。
* 命令名如果和原版或其他插件的命令撞了，插件会改用 `spitplugin:<命令名>` 并给出警告。

---

## 🔐 权限

| 权限       | 说明                    | 默认     |
| ---------- | ----------------------- | -------- |
| `spit.use` | 允许使用吐口水命令      | 所有玩家 |

> 权限节点以 `config.yml` 里的 `command.permission` 为准，上表是默认值。

---

## 🧠 工作原理

玩家执行命令时：

* 沿玩家视线方向生成一只原版羊驼口水（`LLAMA_SPIT`）
* 口水以 1.1–1.3 的速度向前飞出，物理表现与原版羊驼口水一致
* 命中生物时造成 1 点伤害，并播放羊驼吐口水音效

---

## 📌 环境要求

* Minecraft **26.3** 服务器（Spigot 或 Paper）
* Java SE **25**（26.3 服务端要求；插件本身编译为 Java 21 字节码）

---

## 🛠️ 构建

```bash
mvn clean package
```

产物为 `target/spitplugin-<版本>.jar`。想把 jar 顺便拷进测试服：

```bash
mvn clean package -Dserver.plugins.dir=/path/to/server/plugins
```

---

## 📜 更新日志

见 [CHANGELOG.md](CHANGELOG.md)。

---

## 🚀 后续想法

* 状态效果（中毒、缓慢）
* PvP 相关机制

---

## English

A fun, lightweight Spigot/Paper plugin that lets players spit like llamas 🦙.

* **Supported version:** Minecraft **26.3** (Spigot / Paper), Java SE 25
* **Command:** `/spit` (name, aliases, permission and messages are configurable in `config.yml`) — launches a vanilla llama spit projectile towards the crosshair
* **Permission:** `spit.use` (default: everyone), overridable via `command.permission`
* **Build:** `mvn clean package` → `target/spitplugin-<version>.jar`

All in-game messages are in Simplified Chinese.

---

🤝 CONTRIBUTING

Contributions are welcome!

If you have ideas, improvements, or fixes:

* Fork the repository
* Make your changes
* Submit a pull request

All contributions are appreciated 💙

---

## ⭐ Support

If you like this plugin, consider giving it a ⭐ on GitHub!
