package pl.botprzemek.bpLobby.Lobby.Config;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.HashMap;

public class InventoryConfig extends Config {

    public InventoryConfig(BpLobby instance) {

        super(instance, "inventories.yml");

    }

    public String getInventoryTitle(StringSerializer stringSerializer, String inventoryName) {

        return stringSerializer.serializePlainText(getConfigurationSection(inventoryName).getString("title"));

    }

    public int getInventorySize(String inventoryName) {

        return getConfigurationSection(inventoryName).getInt("size");

    }

    public HashMap<Integer, ItemStack> getInventoryItems(String inventoryName) {

        HashMap<Integer, ItemStack> items = new HashMap<>();

        ConfigurationSection itemsConfig = getConfigurationSection(inventoryName + ".items");

        if (itemsConfig == null) return null;

        for (String key : itemsConfig.getKeys(false)) {

            ConfigurationSection itemConfig = itemsConfig.getConfigurationSection(key);

            String itemID = itemConfig.getString("id");

            if (!OraxenItems.exists(itemID)) {

                Bukkit.getLogger().info("Item does not exists (" + itemID + ")");

                return null;

            }

            items.put(itemConfig.getInt("slot"), OraxenItems.getItemById(itemID).build());

        }

        return items;

    }

    public Inventory getFilledInventory(StringSerializer stringSerializer, String inventoryName) {

        Inventory inventory = Bukkit.createInventory(
                null,
                getInventorySize(inventoryName),
                getInventoryTitle(stringSerializer, inventoryName));

        HashMap<Integer, ItemStack> items = getInventoryItems(inventoryName);

        if (items == null) return inventory;

        for (int slot : items.keySet()) inventory.setItem(slot, items.get(slot));

        return inventory;

    }

}
