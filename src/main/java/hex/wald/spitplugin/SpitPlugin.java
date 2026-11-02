package hex.wald.spitplugin;

import hex.wald.spitplugin.command.SpitCommand;
import hex.wald.spitplugin.listener.SpitProjectileListener;
import hex.wald.spitplugin.service.SpitProjectileService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class SpitPlugin extends JavaPlugin {

    private static final String DEFAULT_COMMAND_NAME = "spit";
    private static final String DEFAULT_PERMISSION = "spit.use";
    private static final String DEFAULT_DESCRIPTION = "朝准星方向吐一口口水";
    private static final String DEFAULT_USAGE = "/%command%";
    private static final String DEFAULT_PLAYERS_ONLY = "该命令只能由游戏内玩家执行。";
    private static final String DEFAULT_NO_PERMISSION = "&c你没有权限使用这个命令。";

    private SpitCommand spitCommand;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        SpitProjectileService projectileService = new SpitProjectileService();
        getServer().getPluginManager().registerEvents(new SpitProjectileListener(projectileService), this);

        registerSpitCommand(projectileService);

        getLogger().info("SpitPlugin 已加载，现在可以像羊驼一样吐口水了 :)");
    }

    @Override
    public void onDisable() {
        unregisterSpitCommand();
        getLogger().info("SpitPlugin 已卸载 :(");
    }

    /**
     * 按照 config.yml 里的 command 段动态注册命令，因此命令名、别名、权限都可以改。
     */
    private void registerSpitCommand(SpitProjectileService projectileService) {
        FileConfiguration config = getConfig();

        String name = normalizeCommandName(config.getString("command.name", DEFAULT_COMMAND_NAME));

        if (name == null) {
            name = DEFAULT_COMMAND_NAME;
            getLogger().warning("配置项 command.name 不是合法的命令名，已回退到 " + DEFAULT_COMMAND_NAME + "。");
        }

        List<String> aliases = new ArrayList<String>();
        for (String rawAlias : config.getStringList("command.aliases")) {
            String alias = normalizeCommandName(rawAlias);

            if (alias == null) {
                getLogger().warning("忽略非法的命令别名：" + rawAlias);
                continue;
            }

            if (!alias.equals(name) && !aliases.contains(alias)) {
                aliases.add(alias);
            }
        }

        String permission = config.getString("command.permission", DEFAULT_PERMISSION);
        permission = permission == null || permission.trim().isEmpty() ? null : permission.trim();

        String description = config.getString("command.description", DEFAULT_DESCRIPTION);
        String usage = config.getString("command.usage", DEFAULT_USAGE).replace("%command%", name);
        String playersOnly = colorize(config.getString("messages.players-only", DEFAULT_PLAYERS_ONLY));
        String noPermission = colorize(config.getString("messages.no-permission", DEFAULT_NO_PERMISSION));

        if (permission != null && getServer().getPluginManager().getPermission(permission) == null) {
            getServer().getPluginManager().addPermission(
                    new Permission(permission, "允许使用 /" + name + " 吐口水", PermissionDefault.TRUE));
        }

        CommandMap commandMap = resolveCommandMap(getServer());

        if (commandMap == null) {
            getLogger().severe("无法取得服务器的命令注册表，/" + name + " 将不可用。");
            return;
        }

        Command existing = commandMap.getCommand(name);

        spitCommand = new SpitCommand(name, projectileService, permission, playersOnly, noPermission);
        spitCommand.setAliases(aliases);
        spitCommand.setDescription(description);
        spitCommand.setUsage(usage);
        commandMap.register(getName().toLowerCase(), spitCommand);

        if (existing != null) {
            getLogger().warning("命令 /" + name + " 已被其他插件占用，本插件改用 /" + getName().toLowerCase() + ":" + name + "。");
        }

        if (aliases.isEmpty()) {
            getLogger().info("已注册命令 /" + name);
        } else {
            List<String> rendered = new ArrayList<String>();
            for (String alias : aliases) {
                rendered.add("/" + alias);
            }
            getLogger().info("已注册命令 /" + name + "（别名：" + String.join("、", rendered) + "）");
        }
    }

    private void unregisterSpitCommand() {
        if (spitCommand == null) {
            return;
        }

        CommandMap commandMap = resolveCommandMap(getServer());

        if (commandMap != null) {
            spitCommand.unregister(commandMap);
        }

        spitCommand = null;
    }

    /**
     * Bukkit API 没有暴露 CommandMap，只能反射拿 CraftServer 的实现（Spigot / Paper 都有这个方法）。
     */
    private static CommandMap resolveCommandMap(Server server) {
        try {
            Method method = server.getClass().getMethod("getCommandMap");
            Object commandMap = method.invoke(server);
            return commandMap instanceof CommandMap ? (CommandMap) commandMap : null;
        } catch (ReflectiveOperationException | RuntimeException e) {
            return null;
        }
    }

    /**
     * 去掉可能写进配置的斜杠和空白；含空格的命令名不合法，返回 null。
     */
    private static String normalizeCommandName(String raw) {
        if (raw == null) {
            return null;
        }

        String name = raw.trim();

        while (name.startsWith("/")) {
            name = name.substring(1).trim();
        }

        if (name.isEmpty() || name.chars().anyMatch(Character::isWhitespace)) {
            return null;
        }

        return name;
    }

    private static String colorize(String raw) {
        return ChatColor.translateAlternateColorCodes('&', raw == null ? "" : raw);
    }
}
