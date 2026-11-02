# SpitPlugin 💦

**SpitPlugin** is a fun and lightweight Minecraft plugin for Spigot/Paper servers that allows players to spit like llamas 🦙 — perfect for adding chaos, humor, and unique interactions to your server.

> Supported version: **Minecraft 26.3** (Spigot / Paper). Requires Java SE 25 on the server.

---

## ✨ Features

* 💦 Player command to spit projectiles
* 🎨 Spit type menu (`/spitmenu`) with dust, bone meal, honey and slime effects
* 🔊 Sound effects on launch
* 🔒 Permission-based access

---

## 📥 Installation

1. Download the plugin `.jar`
2. Place it in your server's `plugins` folder
3. Restart or reload the server
4. Done ✅

---

## 📜 Commands

| Command     | Description                       |
| ----------- | --------------------------------- |
| `/spit`     | Shoot a spit projectile           |
| `/spitmenu` | Open the spit type selection menu |

---

## 🔐 Permissions

| Permission | Description                             |
| ---------- | --------------------------------------- |
| `spit.use` | Allows usage of `/spit` and `/spitmenu` |

---

## 🧠 How It Works

When a player uses `/spit`:

* A llama spit projectile is launched forward
* Its flight path is traced with particles that match the selected spit type
* On impact it deals 1 damage, plays the llama spit sound and spawns a particle burst

---

## 📌 Requirements

* Minecraft **26.3** server (Spigot or Paper)
* Java SE **25** (required by the 26.3 server; the plugin itself is built as Java 21 bytecode)

---

## 🛠️ Build

```bash
mvn clean package
```

The plugin jar is written to `target/spitplugin-<version>.jar`. To also copy it into a test server:

```bash
mvn clean package -Dserver.plugins.dir=/path/to/server/plugins
```

---

## 📜 Changelog

See [CHANGELOG.md](CHANGELOG.md).

---

## 🚀 Future Ideas

* Status effects (poison, slowness)
* PvP-focused mechanics
* Custom spit types
* GUI configuration

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
