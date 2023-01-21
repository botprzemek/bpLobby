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
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Utils.PluginManager;

public class ReloadCommand implements CommandExecutor {

    private final ConfigManager configManager;

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

    public ReloadCommand(LobbyManager lobbyManager) {

        configManager = lobbyManager.getConfigManager();

        messageManager = lobbyManager.getMessageManager();

        pluginManager = lobbyManager.getPluginManager();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        try {

            configManager.loadConfigs();
            pluginManager.loadConfigs();

            if (sender instanceof Player player) messageManager.sendCommandMessage(player, "reload.success");

            return true;

        }

        catch (Exception error) {

            messageManager.sendCommandMessage((Player) sender, "reload.failed");

            error.printStackTrace();

            return false;

        }

    }
}
