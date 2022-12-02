package pl.botprzemek.bplobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import pl.botprzemek.bplobby.BpLobby;

import java.util.Collection;

public class ChestManager {

    private final BpLobby instance;

    public ChestManager(BpLobby instance) {

        this.instance = instance;

    }

    public Block createChest(FileConfiguration config, int i) {

        World world = Bukkit.getWorld(config.getString("chest.world"));

        double x = config.getDouble("chest.chests."+i+".x");
        double y = config.getDouble("chest.chests."+i+".y");
        double z = config.getDouble("chest.chests."+i+".z");

        Location chestLocation = new Location(world, x, y, z);

        Block block = chestLocation.getBlock();

        block.setType(Material.CHEST);

        Chest chest = (Chest) chestLocation.getBlock().getState();

        chest.setCustomName("Loot chest");

        ArmorStand armorStand = createArmorstand(block);

        armorStand.setCustomName("Locked");

        lockChest(block);

        return block;

    }

    public void unlockChest(Block block) {

        Location blockLocation = block.getLocation();

        ArmorStand armorStand = getArmorstand(block, blockLocation.getX(), blockLocation.getY(), blockLocation.getZ());

        Chest chest = (Chest) block.getState();

        Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {

            public void run() {

                chest.setLock(null);

                chest.update(true);

                armorStand.setCustomName("Unlocked");

            }

        }, 1L);

    }

    public void lockChest(Block block) {

        Location blockLocation = block.getLocation();

        ArmorStand armorStand = getArmorstand(block, blockLocation.getX(), blockLocation.getY(), blockLocation.getZ());

        Chest chest = (Chest) block.getState();

        Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {

            public void run() {

                chest.setLock("locked");

                chest.update(true);

                armorStand.setCustomName("Locked");

            }

        }, 1L);

    }

    public ArmorStand createArmorstand(Block block) {

        Location blockLocation = block.getLocation();

        blockLocation.setX(blockLocation.getBlockX()+0.5);
        blockLocation.setZ(blockLocation.getBlockZ()+0.5);
        blockLocation.setY(blockLocation.getBlockY()+401);

        ArmorStand armorStand = (ArmorStand) block.getWorld().spawnEntity(blockLocation, EntityType.ARMOR_STAND);

        blockLocation.setY(blockLocation.getBlockY()-400);

        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setCollidable(false);
        armorStand.setMarker(true);
        armorStand.setPersistent(true);
        armorStand.setCustomNameVisible(true);
        armorStand.teleport(blockLocation);

        return armorStand;

    }

    public ArmorStand getArmorstand(Block block, double x, double y, double z) {

        World chestWorld = block.getWorld();

        Location armorStandLocation = new Location(chestWorld, block.getLocation().getX(), block.getLocation().getY()+1, block.getLocation().getZ());

        Collection<Entity> armorStands = chestWorld.getNearbyEntities(armorStandLocation, x, y, z);

        ArmorStand armorStand = null;

        for (Entity entity : armorStands) {

            if (entity instanceof ArmorStand) armorStand = (ArmorStand) entity;

        }

        return armorStand;

    }
}
