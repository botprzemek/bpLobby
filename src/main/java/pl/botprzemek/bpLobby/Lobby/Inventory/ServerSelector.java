package pl.botprzemek.bpLobby.Lobby.Inventory;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ServerSelector {

    private HashMap<UUID, Inventory> serverSelectors;

    private InventoryConfig inventoryConfig;

    private String serverSelectorName;

    private StringSerializer stringSerializer;

    public ServerSelector(LobbyManager lobbyManager, String inventoryName) {

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.serverSelectorName = inventoryName;

        this.serverSelectors = new HashMap<>();

    }

    public Inventory createInventory(UUID playerUUID) {

        Inventory inventory = inventoryConfig.getInventory(Bukkit.getPlayer(playerUUID), stringSerializer, serverSelectorName);

        inventory = setInventoryItems(playerUUID, inventory);

        return inventory;

    }

    public Inventory getInventory(UUID playerUUID) {

        return serverSelectors.get(playerUUID);

    }

    public void removeInventory(UUID playerUUID) {

        serverSelectors.remove(playerUUID);

    }

    public Inventory setInventoryItems(UUID playerUUID, Inventory inventory) {

        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);

        if (items == null) {

            serverSelectors.put(playerUUID, inventory);

            return inventory;

        }

        for (Button item : items) inventory.setItem(item.getSlot(), OraxenItems.getItemById(item.getItemID()).build());

        serverSelectors.put(playerUUID, inventory);

        return inventory;

    }

    public List<Button> getInventoryItems() {

        return inventoryConfig.getInventoryItems(serverSelectorName);

    }

    public ItemStack getInventoryItem(int slot) {

        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);

        for (Button item : items) {

            if (item.getSlot() == slot) return OraxenItems.getItemById(item.getItemID()).build();

        }

        return null;

    }

    public String getServerName(int slot) {

        List<Button> items = inventoryConfig.getInventoryItems(serverSelectorName);

        for (Button item : items) {

            if (item.getSlot() == slot) return item.getAction();

        }

        return null;

    }

}
