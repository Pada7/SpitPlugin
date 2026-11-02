package hex.wald.spitplugin;

import hex.wald.spitplugin.command.SpitCommand;
import hex.wald.spitplugin.listener.SpitProjectileListener;
import hex.wald.spitplugin.service.SpitProjectileService;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class SpitPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("SpitPlugin 已加载，现在可以像羊驼一样吐口水了 :)");

        SpitProjectileService projectileService = new SpitProjectileService();

        getServer().getPluginManager().registerEvents(new SpitProjectileListener(projectileService), this);

        PluginCommand spitCommand = getCommand("spit");

        if (spitCommand != null) {
            spitCommand.setExecutor(new SpitCommand(projectileService));
        } else {
            getLogger().warning("未能在 plugin.yml 中找到 spit 命令，/spit 将不可用。");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("SpitPlugin 已卸载 :(");
    }
}
