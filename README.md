# SpitPlugin 💦

**SpitPlugin** 是一个轻量的 Spigot/Paper 插件：让玩家像羊驼一样吐口水 🦙 —— 朝准星方向发射一只羊驼口水，给服务器添点恶作剧的乐趣。

> 支持版本：**Minecraft 26.3**（Spigot / Paper）。服务器需要 Java SE 25。

---

## ✨ 功能

* 💦 用 `/spit` 朝准星方向吐口水
* 🔊 发射时播放羊驼吐口水的音效
* 💥 命中生物造成 1 点伤害
* 🔒 基于权限控制

---

## 📥 安装

1. 下载插件 `.jar`
2. 放进服务器的 `plugins` 目录
3. 重启服务器
4. 完成 ✅

---

## 📜 命令

| 命令    | 说明                 |
| ------- | -------------------- |
| `/spit` | 朝准星方向吐一口口水 |

---

## 🔐 权限

| 权限       | 说明                    | 默认     |
| ---------- | ----------------------- | -------- |
| `spit.use` | 允许使用 `/spit` 吐口水 | 所有玩家 |

---

## 🧠 工作原理

玩家执行 `/spit` 时：

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
* **Command:** `/spit` — launches a vanilla llama spit projectile towards the crosshair
* **Permission:** `spit.use` (default: everyone)
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
