package pl.botprzemek.bpLobby.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class PlayersCommand implements CommandExecutor {

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    public PlayersCommand(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.hideShowPlayers = lobbyManager.getHideShowPlayers();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        boolean invisible = hideShowPlayers.getHiddenPlayers().get(player.getUniqueId());

        if (invisible) {

            hideShowPlayers.showPlayers(player);

            player.sendMessage(stringSerializer.serializeJoinQuit(player, "players.shown"));

        }

        else {

            hideShowPlayers.hidePlayers(player);

            player.sendMessage(stringSerializer.serializeJoinQuit(player, "players.hidden"));

        }

        return true;

    }
}
