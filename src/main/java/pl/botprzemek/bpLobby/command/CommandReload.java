package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import dev.rollczi.litecommands.platform.LiteSender;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.MessageConfiguration;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;

@Route(name = "bplobby", aliases = "bpl")
@Permission("bplobby.command.reload")
public class CommandReload {
    @Inject private MessageManager messageManager;
    @Inject private PluginConfiguration pluginConfiguration;
    @Inject private MessageConfiguration messageConfiguration;

    @Execute
    public void reload(LiteSender sender) {
        try {
            messageConfiguration.load();
            pluginConfiguration.load();
            if (sender instanceof Player player) messageManager.sendCommandMessage(player, "reload.success");
        }
        catch (Exception error) {
            messageManager.sendCommandMessage((Player) sender, "reload.failed");
            error.printStackTrace();
        }
    }
}
