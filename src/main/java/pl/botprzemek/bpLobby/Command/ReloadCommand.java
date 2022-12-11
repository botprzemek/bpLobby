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

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Inventory.InventoryManager;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.HashMap;

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

        return true;

    }
}
