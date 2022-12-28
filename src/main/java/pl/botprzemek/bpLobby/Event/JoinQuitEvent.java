package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.Lobby.Config.LobbyConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.EventCustomization;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class JoinQuitEvent implements Listener {

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    private ServerSelector serverSelector;

    private LobbyConfig lobbyConfig;

    private EventCustomization eventCustomization;

    public JoinQuitEvent(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.hideShowPlayers = lobbyManager.getHideShowPlayers();

        this.serverSelector = lobbyManager.getServerSelector();

        this.lobbyConfig = lobbyManager.getConfigManager().getLobbyConfig();

        this.eventCustomization = lobbyManager.getEventCustomization();

    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(stringSerializer.serializeJoinQuit(player, "join"));

        hideShowPlayers.hidePlayers(player);

        serverSelector.createInventory(player.getUniqueId());

        eventCustomization.createCustomElements(player);

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(stringSerializer.serializeJoinQuit(player, "quit"));

        serverSelector.removeInventory(player.getUniqueId());

    }

}
