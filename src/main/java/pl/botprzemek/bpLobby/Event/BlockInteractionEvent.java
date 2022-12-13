package pl.botprzemek.bpLobby.Event;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class BlockInteractionEvent implements Listener {

    private LobbyManager lobbyManager;

    public BlockInteractionEvent(LobbyManager lobbyManager) {

        this.lobbyManager = lobbyManager;

    }

    @EventHandler
    public void onBlockDestroy(BlockBreakEvent event) {

        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {

        if (!event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) event.setCancelled(true);

    }

}
