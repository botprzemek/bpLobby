package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import com.mojang.authlib.GameProfile;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.CreateGUI;
import pl.botprzemek.methods.PlayerHead;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static pl.botprzemek.bpLobby.perms;
import static pl.botprzemek.bpLobby.plugin;
import static pl.botprzemek.methods.CreateGUI.guiServers;

public class PlayerServerSelector implements Listener {

    public PlayerServerSelector(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    PlayerHead playerHead = new PlayerHead();

    String headUrl = plugin.getConfig().getString("servers.selector.url");
    String headDisplayName = plugin.getConfig().getString("servers.selector.name");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.server.sound")).toUpperCase().replace(' ', '_');
    int headIndex = plugin.getConfig().getInt("servers.selector.index");
    List<String> headLore = plugin.getConfig().getStringList("servers.selector.lore");

    GameProfile headProfile = new GameProfile(UUID.randomUUID(), null);
    SkullMeta head = playerHead.getCustomHead(headUrl, headProfile, headDisplayName, headLore);
    ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (Objects.equals(perms.getPrimaryGroup(player), "default")) {

            player.getInventory().clear();

            item.setItemMeta(head);
            player.getInventory().setHeldItemSlot(headIndex);
            player.getInventory().setItem(headIndex, item);

        }

    }

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (Objects.equals(perms.getPrimaryGroup(player), "default")) {

            event.setCancelled(true);
            return;

        }

    }

    @EventHandler
    public void onPlayerClick(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (Objects.equals(perms.getPrimaryGroup(player), "default")) {

            event.setCancelled(true);
            return;

        }

    }

    @EventHandler
    public void onPlayerPlace(BlockPlaceEvent event) {

        if (Objects.equals(perms.getPrimaryGroup(event.getPlayer()), "default")) {

            event.getPlayer().getInventory().setItem(headIndex, item);
            event.setCancelled(true);
            return;

        }

    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!(event.getHand() == EquipmentSlot.HAND)) return;

        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();

        if (!(item.getItemMeta() == null)) {

            if (item.getType().name().equals("PLAYER_HEAD")) {

                if (IridiumColorAPI.stripColorFormatting(ChatColor.stripColor(item.getItemMeta().getDisplayName())).equals(IridiumColorAPI.stripColorFormatting(ChatColor.stripColor(headDisplayName)))) {

                    IridiumColorAPI.stripColorFormatting(ChatColor.stripColor(headDisplayName));

                    if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {

                        event.setCancelled(true);

                        player.openInventory(guiServers);
                        player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

                        event.getPlayer().getInventory().setItem(headIndex, item);

                    }

                }

            }

        }

    }

}
