package pl.botprzemek.bpLobby.Lobby.Inventory;

import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.HashMap;
import java.util.UUID;

public class ServerSelector {

    private HashMap<UUID, Inventory> serverSelectors;

    private InventoryConfig inventoryConfig;

    private StringSerializer stringSerializer;

    public ServerSelector(LobbyManager lobbyManager) {

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.serverSelectors = new HashMap<>();

    }

    public Inventory createInventory(UUID playerUUID) {

        Inventory inventory = inventoryConfig.getFilledInventory(stringSerializer, "server-selector");

        serverSelectors.put(playerUUID, inventory);

        return inventory;

    }

    public Inventory getInventory(UUID playerUUID) {

        return serverSelectors.get(playerUUID);

    }

    public void removeInventory(UUID playerUUID) {

        serverSelectors.remove(playerUUID);

    }

}
