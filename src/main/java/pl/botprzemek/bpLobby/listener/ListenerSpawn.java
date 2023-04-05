package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import io.th0rgal.oraxen.api.events.OraxenFurnitureBreakEvent;
import io.th0rgal.oraxen.api.events.OraxenFurniturePlaceEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;

public class ListenerSpawn implements Listener {
    @Inject private ConfigurationPlugin configurationPlugin;

    @EventHandler
    public void onPlayerBlockDestroyEvent(HangingBreakByEntityEvent event) {
        if (!(event.getRemover() instanceof Player player)) {
            event.setCancelled(true);
            return;
        }
        if (player.hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockDestroyEvent(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;
        if (player.hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamageOthersEvent(EntityDamageByEntityEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamagedEvent(EntityDamageEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDrainHunger(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerDamagedByBlockEvent(EntityDamageByBlockEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerWaterFill(PlayerBucketFillEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerWaterEmpty(PlayerBucketEmptyEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onOraxenBlocksBreak(OraxenFurnitureBreakEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onOraxenBlocksPlace(OraxenFurniturePlaceEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (event.getPlayer().getBedSpawnLocation() == null) event.setRespawnLocation(configurationPlugin.getSpawnLocation());
    }

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("bplobby.bypass")) return;
        if (player.getLocation().getY() > configurationPlugin.getLimit()) return;
        player.teleport(configurationPlugin.getSpawnLocation());
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {
        event.setRespawnLocation(configurationPlugin.getSpawnLocation());
    }
}