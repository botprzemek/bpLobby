package pl.botprzemek.bplobby.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bplobby.bpLobby;

public class ChatEvent implements Listener {

    public ChatEvent(bpLobby instance) {
        Bukkit.getPluginManager().registerEvents(this, instance);
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {

        String originalMessage = event.getMessage();

        MiniMessage mm = MiniMessage.miniMessage();

        Component componentMessage = mm.deserialize("<gradient:#ff80f6:#ffcc70><bold>bpLobby</bold></gradient> ");

        String serializedMessage = mm.serialize(componentMessage) + originalMessage;

        event.setMessage(serializedMessage);
    }
}
