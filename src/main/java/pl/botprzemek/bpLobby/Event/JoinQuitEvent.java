package pl.botprzemek.bpLobby.Event;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.EventCustomization;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class JoinQuitEvent implements Listener {

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    private ServerSelector serverSelector;

    private InventoryConfig inventoryConfig;

    private EventCustomization eventCustomization;

    public JoinQuitEvent(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.hideShowPlayers = lobbyManager.getHideShowPlayers();

        this.serverSelector = lobbyManager.getServerSelector();

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.eventCustomization = lobbyManager.getEventCustomization();

    }

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(stringSerializer.serializeJoinQuit(player, "join"));

        hideShowPlayers.hidePlayers(player);

        serverSelector.createInventory(player.getUniqueId());

        eventCustomization.createCustomElements(player);

        if (player.getGameMode().equals(GameMode.SURVIVAL)) player.getInventory().clear();

        if (player.hasPermission("bplobby.server")) player.getInventory().setItem(inventoryConfig.getItemSlot("item"), OraxenItems.getItemById(inventoryConfig.getItemID("item")).build());

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(stringSerializer.serializeJoinQuit(player, "quit"));

        serverSelector.removeInventory(player.getUniqueId());

    }

}
