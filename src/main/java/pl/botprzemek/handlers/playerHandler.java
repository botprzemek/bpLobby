package pl.botprzemek.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.botprzemek.bpLobby;

import java.util.ArrayList;
import java.util.List;

public class playerHandler implements Listener {
    public playerHandler(bpLobby plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Inventory inv = player.getInventory();

        bpLobby config = new bpLobby();

        String material = config.getConfig().getString("fill.material").toUpperCase().replace(' ', '_');
        String name = config.getConfig().getString("fill.name");
        int amount = Integer.parseInt(config.getConfig().getString("fill.amount"));
        for(int i=1;i<=9;++i){
            ItemStack item = new ItemStack(Material.matchMaterial(material), amount);
            ItemMeta meta = item.getItemMeta();
            if(meta != null){
                meta.setDisplayName(name);
                List<String> lore = new ArrayList<>();
                lore.add(String.valueOf(amount));
                meta.setLore(lore);
                item.setItemMeta(meta);
                inv.setItem(i-1, item);
            }
        }
    }
}
