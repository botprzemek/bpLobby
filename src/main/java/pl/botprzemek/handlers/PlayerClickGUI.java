package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.CreateGUI;
import pl.botprzemek.methods.ServerConnect;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class PlayerClickGUI implements Listener {

    public PlayerClickGUI(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public List<ItemStack> guiItems = new ArrayList<>();
    public List<Integer> guiIndexes = new ArrayList<>();

    String prefix = plugin.getConfig().getString("prefix");
    String click = plugin.getConfig().getString("messages.server.success");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.server.sound")).toUpperCase().replace(' ', '_');

    ServerConnect serverConnect = new ServerConnect();
    List<String> serverNames = plugin.getConfig().getStringList("servers.name");

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

            meta.setDisplayName(IridiumColorAPI.stripColorFormatting(name));
            meta.setLore(lore);
            item.setItemMeta(meta);

            guiItems.add(item);
            guiIndexes.add(index);

        }

    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {

        getItems();

        Player player = (Player) event.getWhoClicked();

        if (event.getClickedInventory() == null) event.setCancelled(true);

        if (!(event.getClickedInventory() instanceof CreateGUI)) event.setCancelled(true);

        if (!(guiIndexes.contains(event.getSlot()))) event.setCancelled(true);

        String itemName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
        String server = plugin.getConfig().getString("servers.server." + itemName);

        if (serverNames.contains(itemName)) {

            event.setCancelled(true);

            player.closeInventory();
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

            serverConnect.connectPlayers(player, server);
            serverConnect.sendMessage(player, IridiumColorAPI.process(prefix + click.replace("%server%", itemName)));

        }

    }

}
