package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Inventory.InventoryManager;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class JoinQuitEvent implements Listener {

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    public JoinQuitEvent(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.hideShowPlayers = lobbyManager.getHideShowPlayers();

    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(stringSerializer.serializeJoinQuit(player, "join"));

        Inventory inventory = new InventoryManager(stringSerializer.serializeInventoryTitle("welcome"), InventoryType.DISPENSER, 0).getInventory();

        hideShowPlayers.hidePlayers(player);

        player.openInventory(inventory);

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(stringSerializer.serializeJoinQuit(player, "quit"));

    }

}
