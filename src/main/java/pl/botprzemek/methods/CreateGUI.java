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

    public static List<ItemStack> guiItems = new ArrayList<>();
    public static List<Integer> guiIndexes = new ArrayList<>();

    static int guiSize = plugin.getConfig().getInt("servers.gui.size");
    static String guiName = plugin.getConfig().getString("servers.gui.name");
    static String guiFillingMaterial = plugin.getConfig().getString("servers.gui.filling").toUpperCase().replace(' ', '_');

    public static void getItems() {

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

    public static Inventory createGUI(Player owner, int size, String name, String material) {

        getItems();

        Inventory gui = Bukkit.createInventory(owner, size, name);

        ItemStack guiFilling = new ItemStack(Material.valueOf(material));
        ItemMeta meta = guiFilling.getItemMeta();

        meta.setDisplayName(" ");
        guiFilling.setItemMeta(meta);

        for (int i = 0; i < size; ++i) gui.setItem(i, guiFilling);

        for (int i = 0; i < guiIndexes.size(); ++i) gui.setItem(guiIndexes.get(i), guiItems.get(i));

        return gui;
    }

    public static Inventory guiServers = createGUI(null, guiSize, IridiumColorAPI.process(guiName), guiFillingMaterial);

}
