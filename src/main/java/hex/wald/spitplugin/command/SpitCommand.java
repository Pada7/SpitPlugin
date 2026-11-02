package hex.wald.spitplugin.command;

import hex.wald.spitplugin.service.SpitProjectileService;
import java.util.Collections;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 命令对象由 config.yml 动态创建，因此这里继承 {@link Command} 而不是实现 CommandExecutor。
 */
public class SpitCommand extends Command {

    private final SpitProjectileService projectileService;
    private final String permission;
    private final String playersOnlyMessage;
    private final String noPermissionMessage;

    public SpitCommand(String name, SpitProjectileService projectileService, String permission,
                       String playersOnlyMessage, String noPermissionMessage) {
        super(name);
        this.projectileService = projectileService;
        this.permission = permission;
        this.playersOnlyMessage = playersOnlyMessage;
        this.noPermissionMessage = noPermissionMessage;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(playersOnlyMessage);
            return true;
        }

        Player player = (Player) sender;

        if (permission != null && !permission.isEmpty() && !player.hasPermission(permission)) {
            player.sendMessage(noPermissionMessage);
            return true;
        }

        projectileService.launch(player);
        return true;
    }

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) {
        return Collections.emptyList();
    }
}
