package pl.botprzemek.bpLobby.Event;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;

public class InventoryEvent implements Listener {

    private ServerSelector serverSelector;

    private BungeeChannel bungeeChannel;

    private InventoryConfig inventoryConfig;

    public InventoryEvent(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.bungeeChannel = lobbyManager.getBungeeChannel();

    }

    @EventHandler
    public void onPlayerInteraction(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        Inventory inventory = event.getClickedInventory();

        if (!serverSelector.getInventory(player.getUniqueId()).equals(inventory)) return;

        event.setCancelled(true);

        if (serverSelector.getInventoryItems() == null) player.closeInventory();

        ItemStack item = inventory.getItem(event.getSlot());

        if (!item.isSimilar(serverSelector.getInventoryItem(event.getSlot()))) return;

        player.closeInventory();

        bungeeChannel.sendPlayerToServer(player, serverSelector.getServerName(event.getSlot()));

    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;

        PlayerInventory inventory = event.getPlayer().getInventory();

        if (inventory.getHeldItemSlot() != inventoryConfig.getItemSlot("item")) return;

        if (!inventory.getItemInMainHand().isSimilar(OraxenItems.getItemById(inventoryConfig.getItemID("item")).build())) return;

        event.getPlayer().openInventory(serverSelector.getInventory(event.getPlayer().getUniqueId()));

    }

}
