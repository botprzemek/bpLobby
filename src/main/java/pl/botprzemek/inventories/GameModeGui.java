package pl.botprzemek.inventories;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class GameModeGui {

    public void openGui(Player player) {

        Inventory inv = Bukkit.createInventory(player, 9, "Test");

        player.openInventory(inv);

    }

}
