package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.block.data.BlockData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.FireworkGenerator;
import pl.botprzemek.methods.ItemsGenerator;
import pl.botprzemek.methods.PlayerHead;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static pl.botprzemek.bpLobby.plugin;

public class ClickChest implements Listener {

    public ClickChest(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    PlayerHead playerHead = new PlayerHead();

    String prefix = plugin.getConfig().getString("prefix");
    String headUrl = plugin.getConfig().getString("head.url");
    String headName = plugin.getConfig().getString("head.name");
    String headDisplayName = plugin.getConfig().getString("head.display-name");
    String headClick = plugin.getConfig().getString("messages.head-click");
    String title = plugin.getConfig().getString("head.title.title");
    String subtitle = plugin.getConfig().getString("head.title.subtitle");
    String fireworkShape = plugin.getConfig().getString("head.firework.shape");
    Color fireworkColor = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("head.firework.color")).replace("#", "")), 16));
    Color fireworkFade = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("head.firework.fade")).replace("#", "")), 16));
    int fireworkTime = plugin.getConfig().getInt("head.firework.time");
    int titleTime = 20 * plugin.getConfig().getInt("head.title.time");
    int titleFade = 20 * plugin.getConfig().getInt("head.title.fade");

    FireworkGenerator fireworkGenerator = new FireworkGenerator();

    GameProfile headProfile = new GameProfile(UUID.randomUUID(), headName);
    SkullMeta head = playerHead.getCustomHead(headUrl, headProfile, headDisplayName);

    BlockData air = Material.AIR.createBlockData();

    String droppedItem = plugin.getConfig().getString("messages.drop.chest.on-drop");
    private final List<ItemsGenerator> dropListItems = new ArrayList<>();

    public void dropManager(String path) {

        ConfigurationSection stoneDrop = plugin.getConfig().getConfigurationSection(path);

        assert stoneDrop != null;
        for (String key : stoneDrop.getKeys(false)) {

            ConfigurationSection section = stoneDrop.getConfigurationSection(key);
            dropListItems.add(new ItemsGenerator(section));

        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        item.setItemMeta(head);

        player.getInventory().addItem(item);

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerClick(PlayerInteractEvent event) {

        if (Objects.equals(event.getHand(), EquipmentSlot.HAND)) return;

        Player player = event.getPlayer();
        Block block = event.getClickedBlock();

        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (!(Objects.requireNonNull(event.getClickedBlock()).getType().equals(Material.PLAYER_HEAD))) return;

            Skull headBlock = (Skull) block.getState();
            String headBlockUUID = Objects.requireNonNull(headBlock.getOwnerProfile()).getName();

            assert headBlockUUID != null;
            if (!(headBlockUUID.equals(headName))) return;

            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                player.sendBlockChange(block.getLocation(), air);
            }, 1L);

            player.sendMessage(IridiumColorAPI.process(prefix + headClick.replace("%chest%", head.getDisplayName())));

            player.sendTitle(IridiumColorAPI.process(title.replace("%name%", head.getDisplayName())), IridiumColorAPI.process(subtitle.replace("%amount%", String.valueOf(10))), titleFade, titleTime, titleFade);

            fireworkGenerator.generateFireworks(event, player, block.getLocation(), fireworkShape, fireworkColor, fireworkFade, fireworkTime);

            ThreadLocalRandom random = ThreadLocalRandom.current();
            int randomList = random.nextInt(dropListItems.size());

            ItemsGenerator randomItem = dropListItems.get(randomList);

            ItemStack item = randomItem.makeItem(random, 1);

            player.getInventory().addItem(item);

            player.sendMessage(IridiumColorAPI.process(prefix + droppedItem.replace("%amount%", String.valueOf(item.getAmount())).replace("%item%", Objects.requireNonNull(item.getItemMeta()).getDisplayName())));

        }
    }
}
