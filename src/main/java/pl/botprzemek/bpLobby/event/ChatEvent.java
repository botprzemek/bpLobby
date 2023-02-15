package pl.botprzemek.bpLobby.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

public class ChatEvent implements Listener {

    private final MessageManager messageManager;

    public ChatEvent(LobbyManager lobbyManager) {

        messageManager = lobbyManager.getMessageManager();

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.chat")) {

            event.setFormat(messageManager.getStringMessage(player, "events.chat.success", event.getMessage()));

            return;

        }

        event.setCancelled(true);

        messageManager.sendEventMessage(player, "chat.failed");

        messageManager.playPlayerSound(player, "error");

    }
}
