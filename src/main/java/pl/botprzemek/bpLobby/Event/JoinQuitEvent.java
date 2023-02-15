package pl.botprzemek.bpLobby.Event;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Config.PluginManager;

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

        event.setJoinMessage(messageManager.getStringMessage(player, "events.connect.join", String.valueOf(Bukkit.getOnlinePlayers().size())));

        if (player.getGameMode().equals(GameMode.SURVIVAL)) player.getInventory().clear();

        player.getInventory().setHeldItemSlot(4);

        pluginManager.setNewHiddenPlayer(player);

        pluginManager.setPlayerSelectorItem(player);

        pluginManager.setInventory(player);

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(messageManager.getStringMessage(player, "events.connect.quit", String.valueOf(Bukkit.getOnlinePlayers().size() - 1)));

        pluginManager.clearHiddenPlayer(player);

        pluginManager.removeInventory(player);

    }

}
