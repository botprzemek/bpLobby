//
//
//
//        ItemStack item = new ItemStack(Material.STONE, 1);
//
//                ItemMeta meta = item.getItemMeta();
//
//                meta.setDisplayName(legacyPrefix);
//
//                item.setItemMeta(meta);
//
//                event.getPlayer().getInventory().setItem(0, item);

package pl.botprzemek.bpLobby.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Inventory.InventoryManager;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Utils.StringSerializer;

public class ReloadCommand implements CommandExecutor {

    private ConfigManager configManager;

    private StringSerializer stringSerializer;

    public ReloadCommand(LobbyManager lobbyManager) {

        this.configManager = lobbyManager.getConfigManager();

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        configManager.loadConfigs();

        sender.sendMessage(stringSerializer.serializeText(configManager.getMessageConfig().getMessage("reload")));

        // TEST

        Player player = (Player) sender;

        InventoryManager inventoryManager = new InventoryManager("Test", InventoryType.CHEST, 36);

        Inventory inventory = inventoryManager.getInventory();

        player.openInventory(inventory);

        return true;

    }
}
