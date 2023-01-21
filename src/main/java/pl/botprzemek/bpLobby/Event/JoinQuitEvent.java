package pl.botprzemek.bpLobby.Event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Utils.PluginManager;

public class JoinQuitEvent implements Listener {

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

    public JoinQuitEvent(LobbyManager lobbyManager) {

        messageManager = lobbyManager.getMessageManager();

        pluginManager = lobbyManager.getPluginManager();

    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(messageManager.getMessageString(player, "events.connect.join", String.valueOf(Bukkit.getOnlinePlayers().size())));

        player.teleport(pluginManager.getSpawnLocation());

        if (player.getGameMode().equals(GameMode.SURVIVAL)) player.getInventory().clear();

        pluginManager.setNewHiddenPlayer(player);

        pluginManager.setPlayerSelector(player);

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(messageManager.getMessageString(player, "events.connect.quit"));

        pluginManager.clearHiddenPlayer(player);

    }

}
