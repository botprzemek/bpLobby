package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.lobby.HiddenPlayers;

@Route(name = "vanish", aliases = "gracze")
@Permission("bplobby.command.vanish")
public class CommandVanish {
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;
    @Inject private HiddenPlayers hiddenPlayers;

    @Execute
    public void view(Player player) {
        if (hiddenPlayers.getPlayer(player) == null) {
            hiddenPlayers.hidePlayer(player);
            managerMessage.sendMessage(player, configurationMessage.getCommandsVanish().getShow());
            return;
        }
        hiddenPlayers.showPlayer(player);
        managerMessage.sendMessage(player, configurationMessage.getCommandsVanish().getHide());
    }
}
