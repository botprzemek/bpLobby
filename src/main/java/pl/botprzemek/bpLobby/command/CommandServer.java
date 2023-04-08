package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.gui.GuiInventory;

@Route(name = "server", aliases = "serwer")
@Permission("bplobby.command.server")
public class CommandServer {
    @Inject private Injector injector;

    @Execute
    public void openGUI(Player player) {
        injector.createInstance(GuiInventory.class).getGui().open(player);
    }
}
