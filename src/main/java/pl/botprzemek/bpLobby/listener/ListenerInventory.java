package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.lobby.ManagerPlugin;
import pl.botprzemek.bpLobby.utils.BungeeChannel;

public class ListenerInventory implements Listener {
    @Inject private ManagerPlugin managerPlugin;
    @Inject private ManagerMessage managerMessage;
    @Inject private BungeeChannel bungeeChannel;

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!event.getWhoClicked().hasPermission("bplobby.*")) event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {
        if (event.getPlayer().hasPermission("bplobby.*")) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPermission("bplobby.server")) return;
        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;
        if (!managerPlugin.isPlayerSelectorViable(player)) return;

        player.openInventory(managerPlugin.getInventory(player));
        managerMessage.playSound(player, "step");
    }

//    @EventHandler
//    public void onPlayerInteraction(InventoryClickEvent event) {
//        Inventory inventory = event.getClickedInventory();
//
//        if (!(event.getWhoClicked() instanceof Player player)) return;
//        if (inventory == null) return;
//        if (event.getCurrentItem() == null) return;
//        if (pluginManager.isClickedSelectorViable(event.getCurrentItem())) {
//            messageManager.playSound(player, "error");
//            event.setCancelled(true);
//            return;
//        }
//        if (!pluginManager.isInventorySelector(player, inventory)) return;
//
//        event.setCancelled(true);
//
//        if (pluginManager.getButtons() == null) player.closeInventory();
//
//        if (!player.hasPermission("bplobby.servers." + button.getAction().toLowerCase())) {
//
//            messageManager.playSound(player, "error");
//
//            return;
//
//        }
//
//        ItemStack clickedItem = inventory.getItem(event.getSlot());
//
//        if (clickedItem == null) return;
//
//        if (!clickedItem.isSimilar(OraxenItems.getItemById(button.getItemId()).build())) return;
//
//        player.closeInventory();
//
//        String serverName = button.getAction().substring(0, 1).toUpperCase() + button.getAction().substring(1).toLowerCase();
//
//        bungeeChannel.sendPlayerToServer(player, serverName);
//
//    }
}
