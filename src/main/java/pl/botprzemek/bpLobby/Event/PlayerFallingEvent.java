package pl.botprzemek.bpLobby.Event;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.botprzemek.bpLobby.Lobby.Config.LobbyConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class PlayerFallingEvent implements Listener {

    private LobbyConfig lobbyConfig;

    public PlayerFallingEvent(LobbyManager lobbyManager) {

        this.lobbyConfig = lobbyManager.getConfigManager().getLobbyConfig();

    }

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (player.getLocation().getY() > lobbyConfig.getLobbyLimit()) return;

        if (!player.getGameMode().equals(GameMode.SURVIVAL)) return;

        player.teleport(lobbyConfig.getLobbyLocation());

        player.playSound(player, Sound.valueOf(lobbyConfig.getLobbySound().getString("name").toUpperCase()), (float) lobbyConfig.getLobbySound().getDouble("volume"), (float) lobbyConfig.getLobbySound().getDouble("pitch"));


    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {

        event.setRespawnLocation(lobbyConfig.getLobbyLocation());

        event.getPlayer().playSound(event.getPlayer(), Sound.valueOf(lobbyConfig.getLobbySound().getString("name").toUpperCase()), (float) lobbyConfig.getLobbySound().getDouble("volume"), (float) lobbyConfig.getLobbySound().getDouble("pitch"));


    }

}
