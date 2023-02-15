package pl.botprzemek.bpLobby.Event;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.botprzemek.bpLobby.Lobby.Config.LobbyConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.EventCustomization;

public class PlayerFallingEvent implements Listener {

    private final LobbyConfig lobbyConfig;

    private final EventCustomization eventCustomization;

    public PlayerFallingEvent(LobbyManager lobbyManager) {

        this.lobbyConfig = lobbyManager.getConfigManager().getLobbyConfig();

        this.eventCustomization = lobbyManager.getEventCustomization();

    }

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getLocation().getY() > lobbyConfig.getLobbyLimit()) return;

        if (!player.getGameMode().equals(GameMode.SURVIVAL)) return;

        player.teleport(lobbyConfig.getLobbyLocation());

        eventCustomization.createCustomElements(player);

    }

}
