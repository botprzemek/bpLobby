package pl.botprzemek.methods;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static pl.botprzemek.bpLobby.plugin;

public class CreateGUI {

    public List<ItemStack> guiItems = new ArrayList<>();
    public List<Integer> guiIndexes = new ArrayList<>();

    public void getItems() {

        ConfigurationSection guiConfig = plugin.getConfig().getConfigurationSection("servers.gui.items");

        for (String key : guiConfig.getKeys(false)) {

            ConfigurationSection itemSection = guiConfig.getConfigurationSection(key);

            Material material = Material.valueOf(itemSection.getString("material").toUpperCase(Locale.ROOT).replace(" ", "_"));
            String name = itemSection.getString("name");
            int index = itemSection.getInt("index");
            List<String> lore = itemSection.getStringList("lore");

            ItemStack item = new ItemStack(material);
            ItemMeta meta = item.getItemMeta();

            meta.setDisplayName(IridiumColorAPI.process(name));
            meta.setLore(IridiumColorAPI.process(lore));
            item.setItemMeta(meta);

            guiItems.add(item);
            guiIndexes.add(index);

        }

    }

    public Inventory createGUI(Player owner, int size, String name, String material) {

        getItems();

        Inventory gui = Bukkit.createInventory(owner, size, name);

        ItemStack guiFilling = new ItemStack(Material.valueOf(material));
        ItemStack test = new ItemStack(Material.MAGENTA_BANNER);

        for (int i = 0; i < size; ++i) gui.setItem(i, guiFilling);

        for (int i = 0; i < guiIndexes.size(); ++i) gui.setItem(guiIndexes.get(i), guiItems.get(i));

        return gui;
    }

}
