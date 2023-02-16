package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import dev.rollczi.litecommands.platform.LiteSender;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.config.ConfigManager;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;
import pl.botprzemek.bpLobby.lobby.config.PluginManager;

@Route(name = "bplobby", aliases = "bpl")
@Permission("bplobby.command.reload")
public class CommandReload {
    private final ConfigManager configManager;
    private final MessageManager messageManager;
    private final PluginManager pluginManager;

    public CommandReload(LobbyManager lobbyManager) {
        configManager = lobbyManager.getConfigManager();
        messageManager = lobbyManager.getMessageManager();
        pluginManager = lobbyManager.getPluginManager();
    }

    @Execute
    public void reload(LiteSender sender) {
        try {
            configManager.loadConfigs();
            pluginManager.loadConfigs();
            if (sender instanceof Player player) messageManager.sendCommandMessage(player, "reload.success");
        }
        catch (Exception error) {
            messageManager.sendCommandMessage((Player) sender, "reload.failed");
            error.printStackTrace();
        }
    }
}
