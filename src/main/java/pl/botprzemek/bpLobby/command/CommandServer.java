package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.gui.GuiInventory;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Route(name = "server", aliases = "serwer")
@Permission("bplobby.command.server")
public class CommandServer {
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private Injector injector;
    @Inject private ManagerMessage managerMessage;

    @Execute
    public void openGUI(Player player) {
        this.injector.createInstance(GuiInventory.class).getGui().open(player);
        managerMessage.playSound(player, configurationMessage.getSounds().getClick());
    }
}
