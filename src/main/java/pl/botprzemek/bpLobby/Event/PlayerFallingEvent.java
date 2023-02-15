package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Config.PluginManager;

public class PlayerFallingEvent implements Listener {

    private final PluginManager pluginManager;

    public PlayerFallingEvent(LobbyManager lobbyManager) {

        pluginManager = lobbyManager.getPluginManager();

    }

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;

        if (player.getLocation().getY() > pluginManager.getLimit()) return;

        player.teleport(pluginManager.getSpawnLocation());

        pluginManager.createCustomElements(player);

    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {

        Player player = event.getPlayer();

        event.setRespawnLocation(pluginManager.getSpawnLocation());

        pluginManager.createCustomElements(player);

        pluginManager.setPlayerSelectorItem(player);

    }

}
