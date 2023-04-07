package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.configs.exception.OkaeriException;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiInventory;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Route(name = "bplobby", aliases = "bpl")
@Permission("bplobby.command.reload")
public class CommandReload {
    @Inject private ConfigurationPlugin configurationPlugin;
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;
    @Inject private GuiInventory guiInventory;

    @Execute
    public void reload(CommandSender sender) {
        try {
            configurationMessage.load(true);
            configurationPlugin.load(true);
            managerMessage.sendMessage(sender, configurationMessage.getCommandsReload().getSuccess());
            if (sender instanceof Player player) managerMessage.playSound(player, configurationMessage.getSounds().getActivate());
        }
        catch (OkaeriException error) {
            managerMessage.sendMessage(sender, configurationMessage.getCommandsReload().getFailed());
            if (sender instanceof Player player) managerMessage.playSound(player, configurationMessage.getSounds().getError());
            error.printStackTrace();
        }
    }
}
