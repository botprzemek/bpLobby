package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.botprzemek.bpLobby;
import java.util.List;
import java.util.Objects;

public class SuperPick implements Listener {

    public SuperPick(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    List<Material> ores = bpLobby.plugin.ores;

    @EventHandler
    public void onStoneBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        World world = player.getWorld();
        Block block = event.getBlock();
        Location location = event.getBlock().getLocation();
        String facing = String.valueOf(player.getFacing());
        int pitch = (int)player.getLocation().getPitch();

        int x = (int)location.getBlockX();
        int y = (int)location.getBlockY();
        int z = (int)location.getBlockZ();

        if (ores.contains(block.getType())) {

            block.breakNaturally();

            if(pitch > 45 || pitch < -45){
                Block b1 = new Location(world, x, y, z).getBlock();
                Block b2 = new Location(world, x+1, y, z).getBlock();
                Block b3 = new Location(world, x-1, y, z).getBlock();
                Block b4 = new Location(world, x, y, z+1).getBlock();
                Block b5 = new Location(world, x, y, z-1).getBlock();
                Block b6 = new Location(world, x-1, y, z-1).getBlock();
                Block b7 = new Location(world, x+1, y, z-1).getBlock();
                Block b8 = new Location(world, x-1, y, z+1).getBlock();
                Block b9 = new Location(world, x+1, y, z+1).getBlock();

                //world.strikeLightning(location);

                b1.breakNaturally();
                if(ores.contains(b2.getType()))b2.breakNaturally();
                if(ores.contains(b3.getType()))b3.breakNaturally();
                if(ores.contains(b4.getType()))b4.breakNaturally();
                if(ores.contains(b5.getType()))b5.breakNaturally();
                Bukkit.getScheduler().runTaskLater(bpLobby.plugin, () -> {
                    if(ores.contains(b6.getType()))b6.breakNaturally();
                    if(ores.contains(b7.getType()))b7.breakNaturally();
                    if(ores.contains(b8.getType()))b8.breakNaturally();
                    if(ores.contains(b9.getType()))b9.breakNaturally();
                }, 20L);
            }

            else if(Objects.equals(facing, "NORTH") || Objects.equals(facing, "SOUTH")){
                Block b1 = new Location(world, x, y, z).getBlock();
                Block b2 = new Location(world, x, y-1, z).getBlock();
                Block b3 = new Location(world, x, y+1, z).getBlock();
                Block b4 = new Location(world, x+1, y, z).getBlock();
                Block b5 = new Location(world, x+1, y-1, z).getBlock();
                Block b6 = new Location(world, x+1, y+1, z).getBlock();
                Block b7 = new Location(world, x-1, y, z).getBlock();
                Block b8 = new Location(world, x-1, y-1, z).getBlock();
                Block b9 = new Location(world, x-1, y+1, z).getBlock();

                //world.strikeLightning(location);

                b1.breakNaturally();
                if(ores.contains(b2.getType()))b2.breakNaturally();
                if(ores.contains(b3.getType()))b3.breakNaturally();
                if(ores.contains(b4.getType()))b4.breakNaturally();
                if(ores.contains(b5.getType()))b5.breakNaturally();
                Bukkit.getScheduler().runTaskLater(bpLobby.plugin, () -> {
                    if(ores.contains(b6.getType()))b6.breakNaturally();
                    if(ores.contains(b7.getType()))b7.breakNaturally();
                    if(ores.contains(b8.getType()))b8.breakNaturally();
                    if(ores.contains(b9.getType()))b9.breakNaturally();
                }, 20L);
            }

            else if(Objects.equals(facing, "EAST") || Objects.equals(facing, "WEST")) {
                Block b1 = new Location(world, x, y, z).getBlock();
                Block b2 = new Location(world, x, y - 1, z).getBlock();
                Block b3 = new Location(world, x, y + 1, z).getBlock();
                Block b4 = new Location(world, x, y, z + 1).getBlock();
                Block b5 = new Location(world, x, y - 1, z + 1).getBlock();
                Block b6 = new Location(world, x, y + 1, z + 1).getBlock();
                Block b7 = new Location(world, x, y, z - 1).getBlock();
                Block b8 = new Location(world, x, y - 1, z - 1).getBlock();
                Block b9 = new Location(world, x, y + 1, z - 1).getBlock();

                //world.strikeLightning(location);

                b1.breakNaturally();
                if(ores.contains(b2.getType()))b2.breakNaturally();
                if(ores.contains(b3.getType()))b3.breakNaturally();
                if(ores.contains(b4.getType()))b4.breakNaturally();
                if(ores.contains(b5.getType()))b5.breakNaturally();
                Bukkit.getScheduler().runTaskLater(bpLobby.plugin, () -> {
                    if(ores.contains(b6.getType()))b6.breakNaturally();
                    if(ores.contains(b7.getType()))b7.breakNaturally();
                    if(ores.contains(b8.getType()))b8.breakNaturally();
                    if(ores.contains(b9.getType()))b9.breakNaturally();
                }, 20L);
            }
        }
    }
}
