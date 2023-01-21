package pl.botprzemek.bpLobby.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.MessageManager;

public class KickWithReasonEvent implements Listener {

    private final MessageManager messageManager;

    public KickWithReasonEvent(LobbyManager lobbyManager) {

        messageManager = lobbyManager.getMessageManager();

    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {

        if (event.getPlayer().hasPermission("bplobby.bypass")) event.setCancelled(true);

        if (!event.getReason().equals("You have been idle for too long!")) return;

        event.setReason(messageManager.getStringMessage(event.getPlayer(), "events.disconnect.timeout"));

    }

}
