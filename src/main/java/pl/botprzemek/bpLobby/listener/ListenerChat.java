package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class ListenerChat implements Listener {
    @Inject private ManagerMessage managerMessage;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.chat")) {
//            event.setFormat(messageManager.getStringMessage(player, "events.chat.success", event.getMessage()));
            return;
        }

        event.setCancelled(true);
//        messageManager.sendEventMessage(player, "chat.failed");
        managerMessage.playSound(player, "error");
    }
}
