package pl.botprzemek.bpLobby.Command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.HashMap;
import java.util.UUID;

public class PlayerInventory {

    private HashMap<UUID, Inventory> playerInventories;

    private InventoryConfig inventoryConfig;

    private StringSerializer stringSerializer;

    public PlayerInventory(LobbyManager lobbyManager) {

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    public Inventory createPlayerInventory(Player player) {

        Inventory inventory = Bukkit.createInventory(player, inventoryConfig.getInventorySize("backpack"), inventoryConfig.getInventoryTitle(stringSerializer, "backpack"));

        playerInventories.put(player.getUniqueId(), inventory);

        player.sendMessage("Created inventory: " + playerInventories.get(player.getUniqueId()).getSize());

        return inventory;
    }

    public HashMap<UUID, Inventory> getPlayerInventories() {

        return playerInventories;

    }

    public Inventory getPlayerInventory(Player player) {

        player.sendMessage("Getter works " + playerInventories.get(player.getUniqueId()).getSize());

        return playerInventories.get(player.getUniqueId());

    }

}
