package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import pl.botprzemek.bpLobby;

import static pl.botprzemek.bpLobby.plugin;


public class PlayerHandler implements Listener {

//        String material = bpLobby.plugin.getConfig().getString("fill.material").toUpperCase().replace(' ', '_');
//        String name = bpLobby.plugin.getConfig().getString("fill.name");
//        int amount = Integer.parseInt(bpLobby.plugin.getConfig().getString("fill.amount"));
//        for(int i=0;i<9;++i){
//            inv.setItem(i, null);
//        }
//        for(int i=10;i<36;++i){
//            ItemStack item = new ItemStack(Material.matchMaterial(material), amount);
//            ItemMeta meta = item.getItemMeta();
//            if(meta != null){
//                meta.setDisplayName(name);
//                //List<String> lore = new ArrayList<>();
//                //lore.add("");
//                //meta.setLore(lore);
//                item.setItemMeta(meta);
//                inv.setItem(i, item);
//            }
//        }
}
