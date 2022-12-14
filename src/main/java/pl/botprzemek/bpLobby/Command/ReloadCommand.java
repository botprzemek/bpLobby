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

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class ReloadCommand implements CommandExecutor {

    private ConfigManager configManager;

    private ServerSelector serverSelector;

    private StringSerializer stringSerializer;

    public ReloadCommand(LobbyManager lobbyManager) {

        this.configManager = lobbyManager.getConfigManager();

        this.serverSelector = lobbyManager.getServerSelector();

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {



        for (Player player : Bukkit.getOnlinePlayers()) serverSelector.removeInventory(player.getUniqueId());

        configManager.loadConfigs();

        for (Player player : Bukkit.getOnlinePlayers()) serverSelector.createInventory(player.getUniqueId());

        sender.sendMessage(stringSerializer.serializeText(configManager.getMessageConfig().getMessage("reload")));

        return true;

    }
}
