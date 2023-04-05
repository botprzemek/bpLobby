package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.lobby.ManagerPlugin;

@Route(name = "vanish", aliases = "gracze")
@Permission("bplobby.command.vanish")
public class CommandVanish {
    @Inject private ManagerPlugin managerPlugin;
    @Inject private ManagerMessage managerMessage;

    @Execute
    public void view(Player player) {
        boolean invisible = managerPlugin.isHiddenPlayer(player);
        if (invisible) {
            managerPlugin.clearHiddenPlayer(player);
            managerMessage.sendCommandMessage(player, "vanish.show");
        }
        else {
            managerPlugin.setHiddenPlayer(player);
            managerMessage.sendCommandMessage(player, "vanish.hide");
        }
    }
}
