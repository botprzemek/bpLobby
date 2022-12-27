package pl.botprzemek.bpLobby.Event;

import io.th0rgal.oraxen.api.events.OraxenFurnitureBreakEvent;
import io.th0rgal.oraxen.api.events.OraxenFurniturePlaceEvent;
import org.bukkit.GameMode;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class BlockInteractionEvent implements Listener {

    private LobbyManager lobbyManager;

    public BlockInteractionEvent(LobbyManager lobbyManager) {

        this.lobbyManager = lobbyManager;

    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onItemFrameDestroy(HangingBreakEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (player.getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onItemFrameDestroyByPlayer(HangingBreakByEntityEvent event) {

        if (!(event.getRemover() instanceof Player player)) return;

        if (player.getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onItemFramePlace(HangingPlaceEvent event) {

        if (!(event.getEntity() instanceof ItemFrame)) return;

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onItemFrameEdit(PlayerInteractEntityEvent event) {

        if (!(event.getRightClicked() instanceof ItemFrame)) return;

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onOraxenBlocksBreak(OraxenFurnitureBreakEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onOraxenBlocksPlace(OraxenFurniturePlaceEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

}
