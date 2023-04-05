package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Route(name = "bplobby", aliases = "bpl")
@Permission("bplobby.command.reload")
public class CommandReload {
    @Inject private ConfigurationPlugin configurationPlugin;
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;

    @Execute
    public void reload(CommandSender sender) {
        try {
            configurationMessage.load();
            configurationPlugin.load();
            managerMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
        }
        catch (OkaeriException error) {
            managerMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            error.printStackTrace();
        }
    }
}
