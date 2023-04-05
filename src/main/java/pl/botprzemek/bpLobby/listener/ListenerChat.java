package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class ListenerChat implements Listener {
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("bplobby.chat")) {
            managerMessage.sendMessage(player, configurationMessage.getEventsChat().getFailed(), event.getMessage());
            return;
        }
        event.setCancelled(true);
        managerMessage.sendMessage(player, configurationMessage.getEventsChat().getFailed());
        managerMessage.playSound(player, configurationMessage.getSounds().getError());
    }
}
