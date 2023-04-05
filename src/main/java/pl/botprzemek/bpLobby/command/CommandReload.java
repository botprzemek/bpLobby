package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import dev.rollczi.litecommands.platform.LiteSender;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.MessageConfiguration;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Route(name = "bplobby", aliases = "bpl")
@Permission("bplobby.command.reload")
public class CommandReload {
    @Inject private PluginConfiguration pluginConfiguration;
    @Inject private MessageConfiguration messageConfiguration;
    @Inject private ManagerMessage managerMessage;

    @Execute
    public void reload(LiteSender sender) {
        try {
            messageConfiguration.load();
            pluginConfiguration.load();
            if (sender instanceof Player player) managerMessage.sendCommandMessage(player, "reload.success");
        }
        catch (Exception error) {
            if (sender instanceof Player player) managerMessage.sendCommandMessage(player, "reload.failed");
            error.printStackTrace();
        }
    }
}
