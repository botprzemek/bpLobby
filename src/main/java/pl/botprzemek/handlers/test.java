package pl.botprzemek.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.botprzemek.bpLobby;

public class test implements Listener {
    public test(bpLobby plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onBlockPlace_Low(BlockPlaceEvent event){
        if(event.getPlayer().hasPermission("bpLobby.place")){
            if(event.getBlock().getType() == Material.ANDESITE){
                event.getBlock().setType(Material.GREEN_CONCRETE);
            }
        }
        else {
            return;
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace_Normal(BlockPlaceEvent event){
        Block block = event.getBlock();

        if(block.getType() != Material.ANDESITE){
            return;
        }

        Bukkit.getLogger().info("Block was placed");
    }
}
