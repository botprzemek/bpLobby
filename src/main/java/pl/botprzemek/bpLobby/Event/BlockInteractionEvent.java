package pl.botprzemek.bpLobby.Event;

import io.th0rgal.oraxen.api.events.OraxenFurnitureBreakEvent;
import io.th0rgal.oraxen.api.events.OraxenFurniturePlaceEvent;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.PluginManager;

import java.util.Objects;

public class BlockInteractionEvent implements Listener {

    private final PluginManager pluginManager;

    public BlockInteractionEvent(LobbyManager lobbyManager) {

        pluginManager = lobbyManager.getPluginManager();

    }


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

        if (event.getDamager() instanceof Player player) {

            if (player.hasPermission("bplobby.bypass")) return;

            event.setCancelled(true);

        }

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerDamagedEvent(EntityDamageEvent event) {

        if (event.getEntity() instanceof Player player) {

            if (player.hasPermission("bplobby.bypass")) return;

            event.setCancelled(true);

        }

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
    public void onPlayerRespawn(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        if (player.getBedSpawnLocation() == null) event.setRespawnLocation(pluginManager.getSpawnLocation());

    }

    @EventHandler
    public void onPlayerBlockPlace(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerWaterFill(PlayerBucketFillEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerWaterEmpty(PlayerBucketEmptyEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onOraxenBlocksBreak(OraxenFurnitureBreakEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onOraxenBlocksPlace(OraxenFurniturePlaceEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        event.setCancelled(true);

    }

}