package pl.botprzemek.bpLobby.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Config.PluginManager;

public class PlayersCommand implements CommandExecutor {

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

    public PlayersCommand(LobbyManager lobbyManager) {

        this.messageManager = lobbyManager.getMessageManager();

        this.pluginManager = lobbyManager.getPluginManager();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        boolean invisible = pluginManager.isHiddenPlayer(player);

        if (invisible) {

            pluginManager.clearHiddenPlayer(player);

            messageManager.sendCommandMessage(player, "vanish.show");

        }

        else {

            pluginManager.setHiddenPlayer(player);

            messageManager.sendCommandMessage(player, "vanish.hide");

        }

        return true;

    }
}
