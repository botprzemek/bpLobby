package pl.botprzemek.bpLobby.Lobby.Config;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Inventory.Button;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.ArrayList;
import java.util.List;

public class InventoryConfig extends Config {

    public InventoryConfig(BpLobby instance) {

        super(instance, "inventories.yml");

    }

    public int getItemSlot(String name) {

        return getConfigurationSection(name).getInt("slot");

    }

    public String getItemID(String name) {

        return getConfigurationSection(name).getString("id");

    }

    public String getInventoryTitle(Player player, StringSerializer stringSerializer, String inventoryName) {

        return stringSerializer.serializePlainTextWithPapi(player, getConfigurationSection(inventoryName).getString("title"));

    }

    public int getInventorySize(String inventoryName) {

        return getConfigurationSection(inventoryName).getInt("size");

    }

    public List<Button> getInventoryItems(String inventoryName) {

        List<Button> inventoryData = new ArrayList<>();

        ConfigurationSection itemsConfig = getConfigurationSection(inventoryName + ".items");

        if (itemsConfig == null) return null;

        for (String key : itemsConfig.getKeys(false)) {

            ConfigurationSection itemConfig = itemsConfig.getConfigurationSection(key);

            inventoryData.add(new Button(itemConfig.getInt("slot"), itemConfig.getString("id"), itemConfig.getString("action")));

        }

        return inventoryData;

    }

    public List<String> getServerNames(String inventoryName) {

        List<String> inventoryData = new ArrayList<>();

        ConfigurationSection itemsConfig = getConfigurationSection(inventoryName + ".items");

        if (itemsConfig == null) return null;

        for (String key : itemsConfig.getKeys(false)) {

            ConfigurationSection itemConfig = itemsConfig.getConfigurationSection(key);

            inventoryData.add(itemConfig.getString("action"));

        }

        return inventoryData;

    }

    public Inventory getInventory(Player player, StringSerializer stringSerializer, String inventoryName) {

        Inventory inventory = Bukkit.createInventory(
                null,
                getInventorySize(inventoryName),
                getInventoryTitle(player, stringSerializer, inventoryName));

        return inventory;

    }

}
