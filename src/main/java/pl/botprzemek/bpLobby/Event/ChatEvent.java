package pl.botprzemek.bpLobby.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class ChatEvent implements Listener {

    private final StringSerializer stringSerializer;

    public ChatEvent(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        if (!(event.getPlayer().hasPermission("bplobby.chat"))) {

            event.setCancelled(true);

            event.getPlayer().sendMessage(stringSerializer.serializeTextFromPath("chat.no-permission", event.getPlayer(), event.getMessage()));

            return;

        }

        event.setFormat(stringSerializer.serializeTextFromPath("chat.permission", event.getPlayer(), event.getMessage()));

    }
}
