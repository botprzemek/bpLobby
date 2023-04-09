package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiType;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Route(name = "server", aliases = "serwer")
@Permission("bplobby.command.server")
public class CommandServer {
    @Inject
    private ConfigurationPlugin configurationPlugin;
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ManagerMessage managerMessage;
    @Inject
    private BungeeChannel bungeeChannel;

    @Execute
    public void openGUI(Player player) {
        GuiType.SERVER.open(player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
    }
}
