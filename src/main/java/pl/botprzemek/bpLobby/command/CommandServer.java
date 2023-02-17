package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.argument.Arg;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;
import pl.botprzemek.bpLobby.lobby.config.PluginManager;

@Route(name = "serwer", aliases = "server")
@Permission("bplobby.command.server")
public class CommandServer {
    @Inject private MessageManager messageManager;
    @Inject private PluginManager pluginManager;

    @Execute
    public void openGUI(Player player) {
        player.openInventory(pluginManager.getInventory(player));
        messageManager.playSound(player, "activate");
    }

    @Execute(required = 1)
    public void sendPlayer(Player player, @Arg String serverName) {
        serverName = serverName.substring(0, 1).toUpperCase() + serverName.substring(1).toLowerCase();

        if (!pluginManager.isServerViable(serverName.toLowerCase())) {
            messageManager.sendCommandMessage(player, "server.invalid", serverName);
            messageManager.playSound(player, "error");
            return;
        }

        if (!player.hasPermission("bplobby.server." + serverName.toLowerCase())) {
            messageManager.sendCommandMessage(player, "server.not-found", serverName);
            messageManager.playSound(player, "error");
            return;
        }

        bungeeChannel.sendPlayer(player, serverName);
        messageManager.playSound(player, "activate");
    }
}
