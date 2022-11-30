package pl.botprzemek.bplobby.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bplobby.BpLobby;
import pl.botprzemek.bplobby.utils.StringSerialize;

public class ChatEvent implements Listener {

    private final BpLobby instance;

    public ChatEvent(BpLobby instance) {

        this.instance = instance;

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        FileConfiguration config = BpLobby.getInstanceConfig();

        if (!(event.getPlayer().hasPermission("bplobby.chat"))) {

            event.setCancelled(true);

            String legacyPrefix = new StringSerialize().serializeString(event.getPlayer(), config.getString("chat.no-permission")
                    .replace("%prefix%", config.getString("prefix")));

            event.getPlayer().sendMessage(legacyPrefix);

            return;

        }

        String originalMessage = event.getMessage();

        String legacyPrefix = new StringSerialize().serializeString(event.getPlayer(), config.getString("chat.format"));

        event.setFormat(legacyPrefix + originalMessage);

    }
}
