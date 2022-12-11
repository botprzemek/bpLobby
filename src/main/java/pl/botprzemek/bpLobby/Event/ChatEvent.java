package pl.botprzemek.bpLobby.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Utils.StringSerializer;

public class ChatEvent implements Listener {

    private final StringSerializer stringSerializer;

    public ChatEvent(LobbyManager lobbyManager) {

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        if (!(event.getPlayer().hasPermission("bplobby.chat"))) {

            event.setCancelled(true);

            event.getPlayer().sendMessage(stringSerializer.serializeTextFromPath("chat.no-permission"));

            return;

        }

        event.setFormat(stringSerializer.serializeTextFromPath("chat.permission").replace("%message%", event.getMessage()));

    }
}
