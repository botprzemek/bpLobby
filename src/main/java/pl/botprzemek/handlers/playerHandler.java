package pl.botprzemek.handlers;

import com.sun.tools.javac.Main;
import net.kyori.adventure.Adventure;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
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
import org.checkerframework.checker.nullness.qual.NonNull;
import pl.botprzemek.bpLobby;

import static pl.botprzemek.bpLobby.plugin;


public class PlayerHandler implements Listener {

    public PlayerHandler(bpLobby plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){

        Audience new_user = plugin.adventure().player(event.getPlayer());
        var mm = MiniMessage.miniMessage();

        Component parsed = mm.deserialize("<rainbow>Witaj " + event.getPlayer().getDisplayName() + "!</rainbow>");
        event.setJoinMessage(null);

        new_user.sendMessage(parsed);

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

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event){

        Player player = event.getPlayer();
        Inventory inv = player.getInventory();

        event.setQuitMessage(player.getDisplayName() + " wyszedł z serwera!");

    }
}
