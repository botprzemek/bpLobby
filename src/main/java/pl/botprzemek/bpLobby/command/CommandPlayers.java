package pl.botprzemek.bpLobby.command;

import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.route.Route;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.Config.PluginManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

@Route(name = "players", aliases = "widok")
@Permission("bplobby.command.players")
public class CommandPlayers {
    private final MessageManager messageManager;
    private final PluginManager pluginManager;

    public CommandPlayers(LobbyManager lobbyManager) {
        this.messageManager = lobbyManager.getMessageManager();
        this.pluginManager = lobbyManager.getPluginManager();
    }

    @Execute
    public void viewPlayer(Player player) {
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
