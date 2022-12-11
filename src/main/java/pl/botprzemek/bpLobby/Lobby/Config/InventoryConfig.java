package pl.botprzemek.bpLobby.Lobby.Config;

import pl.botprzemek.bpLobby.BpLobby;

public class InventoryConfig extends Config {

    public InventoryConfig(BpLobby instance) {

        super(instance, "inventories.yml");

    }

    public String getInventoryTitle(String inventoryName) {

        return getConfigurationSection(inventoryName).getString("title");

    }

    public int getInventorySize(String inventoryName) {

        return getConfigurationSection(inventoryName).getInt("size");

    }

}
