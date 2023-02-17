package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;
import pl.botprzemek.bpLobby.lobby.config.PluginManager;

@Route(name = "vanish", aliases = "widok")
@Permission("bplobby.command.vanish")
public class CommandPlayers {
    @Inject private MessageManager messageManager;
    @Inject private PluginManager pluginManager;

    @Execute
    public void view(Player player) {
        boolean invisible = pluginManager.isHiddenPlayer(player);
        if (invisible) {
            pluginManager.clearHiddenPlayer(player);
            messageManager.sendCommandMessage(player, "vanish.show");
        }
        else {
            pluginManager.setHiddenPlayer(player);
            messageManager.sendCommandMessage(player, "vanish.hide");
        }
    }
}
