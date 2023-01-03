package pl.botprzemek.bpLobby.Event;

import io.th0rgal.oraxen.api.OraxenItems;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.Config.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;

public class InventoryEvent implements Listener {

    private ServerSelector serverSelector;

    private BungeeChannel bungeeChannel;

    private InventoryConfig inventoryConfig;

    private MessageConfig messageConfig;

    public InventoryEvent(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

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

        if (item == null || !item.isSimilar(serverSelector.getInventoryItem(event.getSlot()))) return;

        player.closeInventory();

        bungeeChannel.sendPlayerToServer(player, serverSelector.getServerName(event.getSlot()));

    }

    @EventHandler
    public void onPlayerItemDrop(PlayerDropItemEvent event) {

        if (event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) event.setCancelled(true);

    }

    @EventHandler
    public void onPlayerRightClick(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission("bplobby.server")) return;

        if (!(event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) return;

        ItemStack item = event.getItem();

        if (item == null || !item.isSimilar(OraxenItems.getItemById(inventoryConfig.getItemID("item")).build())) return;

        player.openInventory(serverSelector.getInventory(player.getUniqueId()));

        player.playSound(player, Sound.valueOf(messageConfig.getString("server.success.sound").toUpperCase()), 1, 1);

    }

}
