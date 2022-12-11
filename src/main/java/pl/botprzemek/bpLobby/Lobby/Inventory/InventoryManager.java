package pl.botprzemek.bpLobby.Lobby.Inventory;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryManager {

    private Inventory inventory;

    public InventoryManager(String inventoryName, InventoryType inventoryType, int inventorySize) {

        this.inventory = (inventoryType.equals(InventoryType.CHEST)) ? Bukkit.createInventory(null, inventorySize, inventoryName) : Bukkit.createInventory(null, inventoryType, inventoryName);

    }

    public void setInventoryItems(HashMap<Integer, ItemStack> itemList) {

        for (int slot : itemList.keySet()) inventory.setItem(slot, itemList.get(slot));

    }

    public Inventory getInventory() {

        return inventory;

    }

}
