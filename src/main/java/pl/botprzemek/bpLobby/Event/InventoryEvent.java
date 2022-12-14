package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;

public class InventoryEvent implements Listener {

    private ServerSelector serverSelector;

    private BungeeChannel bungeeChannel;

    public InventoryEvent(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

        this.bungeeChannel = lobbyManager.getBungeeChannel();

    }

    @EventHandler
    public void onPlayerInteraction(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        Inventory inventory = event.getClickedInventory();

        if (!serverSelector.getInventory(player.getUniqueId()).equals(inventory)) return;

        event.setCancelled(true);

        if (serverSelector.getInventoryItems() == null) player.closeInventory();

        if (!inventory.getItem(event.getSlot()).isSimilar(serverSelector.getInventoryItem(event.getSlot()))) return;

        player.closeInventory();

        bungeeChannel.sendPlayerToServer(player, serverSelector.getServerName(event.getSlot()));

    }

}
