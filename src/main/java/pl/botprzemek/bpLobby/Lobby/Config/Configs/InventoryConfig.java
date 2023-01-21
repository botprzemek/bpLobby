package pl.botprzemek.bpLobby.Lobby.Config.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Config.Config;
import pl.botprzemek.bpLobby.Lobby.Inventory.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InventoryConfig extends Config {

    public InventoryConfig(BpLobby instance, String file) {

        super(instance, file);

    }

    public String getItemID(String name) {

        if (getConfigurationSection(name) != null) return Objects.requireNonNull(getConfigurationSection(name)).getString("id");

        return null;

    }

    public String getInventoryTitle(Player player, String inventoryName) {

        if (getConfigurationSection(inventoryName) != null) return instance.getLobbyManager().getMessageManager().getMessageString(player, Objects.requireNonNull(getConfigurationSection(inventoryName)).getString("title"));

        return null;

    }

    public int getInventorySize(String inventoryName) {

        if (getConfigurationSection(inventoryName) != null) return Objects.requireNonNull(getConfigurationSection(inventoryName)).getInt("size");

        else return -1;

    }

    public List<Button> getInventoryItems(String inventoryName) {

        List<Button> inventoryData = new ArrayList<>();

        ConfigurationSection itemsConfig = getConfigurationSection(inventoryName + ".items");

        if (itemsConfig == null) return null;

        for (String key : itemsConfig.getKeys(false)) {

            ConfigurationSection itemConfig = itemsConfig.getConfigurationSection(key);

            if (itemConfig != null) inventoryData.add(new Button(itemConfig.getInt("slot"), itemConfig.getString("id"), itemConfig.getString("action")));

        }

        return inventoryData;

    }

    public List<String> getServerNames(String inventoryName) {

        List<String> inventoryData = new ArrayList<>();

        ConfigurationSection itemsConfig = getConfigurationSection(inventoryName + ".items");

        if (itemsConfig == null) return null;

        for (String item : itemsConfig.getKeys(false)) {

            ConfigurationSection itemConfig = itemsConfig.getConfigurationSection(item);

            if (itemConfig == null) return null;

            if (itemConfig.getString("action") != null) inventoryData.add(Objects.requireNonNull(itemConfig.getString("action")).substring(0, 1).toUpperCase() + Objects.requireNonNull(itemConfig.getString("action")).substring(1).toLowerCase());

        }

        return inventoryData;

    }

    public Inventory getInventory(Player player, String inventoryName) {

        return Bukkit.createInventory(
                null,
                getInventorySize(inventoryName),
                getInventoryTitle(player, inventoryName));

    }

}
