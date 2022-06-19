package pl.botprzemek.methods;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.botprzemek.bpLobby;

import static pl.botprzemek.bpLobby.plugin;

public class LaunchPadFall implements Listener {

    public LaunchPadFall(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerFall(EntityDamageEvent event) {
        if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if(plugin.jumpingPlayers.contains(event.getEntity())) {
                event.setCancelled(true);
                plugin.jumpingPlayers.remove(event.getEntity());
            }
        }
    }
}
