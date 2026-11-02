package hex.wald.spitplugin.service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LlamaSpit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class SpitProjectileService {

    private final Set<UUID> trackedProjectiles = new HashSet<UUID>();

    public void launch(Player player) {
        Location location = player.getEyeLocation().add(player.getLocation().getDirection().multiply(0.8));
        LlamaSpit spit = (LlamaSpit) player.getWorld().spawnEntity(location, EntityType.LLAMA_SPIT);
        spit.setShooter(player);
        spit.setVelocity(player.getEyeLocation().getDirection().multiply(randomSpeed()));

        trackedProjectiles.add(spit.getUniqueId());
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LLAMA_SPIT, 1.2f, 0.9f);
    }

    public void handleDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile)) {
            return;
        }

        if (trackedProjectiles.contains(event.getDamager().getUniqueId())) {
            event.setDamage(1.0);
        }
    }

    public void handleHit(ProjectileHitEvent event) {
        trackedProjectiles.remove(event.getEntity().getUniqueId());
    }

    private double randomSpeed() {
        return 1.1 + (Math.random() * 0.2);
    }
}
