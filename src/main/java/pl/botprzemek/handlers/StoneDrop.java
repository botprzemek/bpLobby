package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.ItemsGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static pl.botprzemek.bpLobby.plugin;

public class StoneDrop implements Listener {

    public StoneDrop(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    String prefix = plugin.getConfig().getString("prefix");
    String droppedItem = plugin.getConfig().getString("messages.drop.stone");

    private final List<ItemsGenerator> dropListItems = new ArrayList<>();

    public void dropManager() {

        ConfigurationSection stoneDrop = plugin.getConfig().getConfigurationSection("drop.stone");

        assert stoneDrop != null;
        for (String key : stoneDrop.getKeys(false)) {

            ConfigurationSection section = stoneDrop.getConfigurationSection(key);
            dropListItems.add(new ItemsGenerator(section));

        }

    }

    @EventHandler
    public void onStoneBreak(BlockBreakEvent event) {

        Player player = event.getPlayer();
        Block block = event.getBlock();
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int randomList = random.nextInt(dropListItems.size());

        ItemsGenerator randomItem = dropListItems.get(randomList);

        if (!event.getBlock().getType().equals(Material.STONE)) return;

        if (!randomItem.shouldDrop(random)) block.breakNaturally();

        if (randomItem.shouldDrop(random)) {

            event.setDropItems(false);
            ItemStack item = randomItem.makeItem(random);
            player.sendMessage(IridiumColorAPI.process(prefix + droppedItem.replace("%amount%", String.valueOf(item.getAmount())).replace("%item%", Objects.requireNonNull(item.getItemMeta()).getDisplayName())));
            block.getWorld().dropItem(block.getLocation(), item);
            block.breakNaturally();

        }

    }

}
