package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.MessageManager;

public class ChatEvent implements Listener {

    private final MessageManager messageManager;

    public ChatEvent(LobbyManager lobbyManager) {

        messageManager = lobbyManager.getMessageManager();

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.chat")) {

            event.setFormat(messageManager.getMessageString(player, "event.chat.success", event.getMessage()));

            return;

        }

        event.setCancelled(true);

        messageManager.sendEventMessage(player, "chat.failed");

        messageManager.playPlayerSound(player, "error");

    }
}
