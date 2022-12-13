package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class InventoryEvent implements Listener {

    private ServerSelector serverSelector;

    public InventoryEvent(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

    }

    @EventHandler
    public void onPlayerInteraction(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        Inventory inventory = event.getClickedInventory();

        if (serverSelector.getInventory(player.getUniqueId()).equals(inventory)) event.setCancelled(true);

    }

}
