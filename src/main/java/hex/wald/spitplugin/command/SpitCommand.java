package hex.wald.spitplugin.command;

import hex.wald.spitplugin.service.SpitProjectileService;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpitCommand implements CommandExecutor {

    private final SpitProjectileService projectileService;

    public SpitCommand(SpitProjectileService projectileService) {
        this.projectileService = projectileService;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("该命令只能由游戏内玩家执行。");
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("spit.use")) {
            player.sendMessage(ChatColor.RED + "你没有权限使用这个命令。");
            return true;
        }

        projectileService.launch(player);
        return true;
    }
}
