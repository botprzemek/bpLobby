package pl.botprzemek.bpLobby.event;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.Config.PluginManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.Utils.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.Utils.Button;

public class InventoryEvent implements Listener {

    private final BungeeChannel bungeeChannel;

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

    public InventoryEvent(LobbyManager lobbyManager) {

        bungeeChannel = lobbyManager.getBungeeChannel();

        messageManager = lobbyManager.getMessageManager();

        pluginManager = lobbyManager.getPluginManager();

    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (player.hasPermission("bplobby.*")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerInteraction(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory inventory = event.getClickedInventory();

        if (inventory == null) return;

        if (event.getCurrentItem() == null) return;

        if (pluginManager.isClickedSelectorViable(event.getCurrentItem())) {

            messageManager.playPlayerSound(player, "error");

            event.setCancelled(true);

            return;

        }

        if (!pluginManager.isInventorySelector(player, inventory)) return;

        event.setCancelled(true);

        if (pluginManager.getButtons() == null) player.closeInventory();

        Button button = pluginManager.getButtonBySlot(event.getSlot());

        if (button == null) return;

        if (!player.hasPermission("bplobby.servers." + button.getAction().toLowerCase())) {

            messageManager.playPlayerSound(player, "error");

            return;

        }

        ItemStack clickedItem = inventory.getItem(event.getSlot());

        if (clickedItem == null) return;

        if (!clickedItem.isSimilar(OraxenItems.getItemById(button.getItemId()).build())) return;

        player.closeInventory();

        String serverName = button.getAction().substring(0, 1).toUpperCase() + button.getAction().substring(1).toLowerCase();

        bungeeChannel.sendPlayerToServer(player, serverName);

    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.*")) return;

        event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("bplobby.server")) return;

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;

        if (!pluginManager.isPlayerSelectorViable(player)) return;

        player.openInventory(pluginManager.getInventory(player));

        messageManager.playPlayerSound(player, "step");

    }

}
