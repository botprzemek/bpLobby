package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.Config.PluginManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.Utils.BungeeChannel;

@Route(name = "serwer", aliases = "server")
@Permission("bplobby.command.server")
public class CommandServer {
    private final BungeeChannel bungeeChannel;
    private final MessageManager messageManager;
    private final PluginManager pluginManager;

    public CommandServer(LobbyManager lobbyManager) {
        bungeeChannel = lobbyManager.getBungeeChannel();
        messageManager = lobbyManager.getMessageManager();
        pluginManager = lobbyManager.getPluginManager();
    }

    @Execute
    public void openGUI(Player player) {
        player.openInventory(pluginManager.getInventory(player));
        messageManager.playPlayerSound(player, "activate");
    }

    @Execute(required = 1)
    public void sendPlayer(Player player, @Arg String serverName) {
        serverName = serverName.substring(0, 1).toUpperCase() + serverName.substring(1).toLowerCase();

        if (!pluginManager.isServerViable(serverName.toLowerCase())) {
            messageManager.sendCommandMessage(player, "server.invalid", serverName);
            messageManager.playPlayerSound(player, "error");
            return;
        }

        if (!player.hasPermission("bplobby.server." + serverName.toLowerCase())) {
            messageManager.sendCommandMessage(player, "server.not-found", serverName);
            messageManager.playPlayerSound(player, "error");
            return;
        }

        bungeeChannel.sendPlayerToServer(player, serverName);
        messageManager.playPlayerSound(player, "activate");
    }
}
