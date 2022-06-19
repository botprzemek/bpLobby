package pl.botprzemek.methods;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;
import pl.botprzemek.bpLobby;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static pl.botprzemek.bpLobby.plugin;

public class LaunchPadFall implements Listener {

    public LaunchPadFall(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    ParticleGenerator particleGenerator = new ParticleGenerator();

    @EventHandler
    public void playerFall(EntityDamageEvent event) {

        if (!(event.getCause().equals(EntityDamageEvent.DamageCause.FALL))) return;

        if (plugin.jumpingPlayers.contains(event.getEntity())) {

            event.setCancelled(true);
            plugin.jumpingPlayers.remove(event.getEntity());

            if(event.getEntity() instanceof Player)
            {
                Player player = (Player) event.getEntity();
                particleGenerator.onFallParticle(player);
            }
        }
    }
}
