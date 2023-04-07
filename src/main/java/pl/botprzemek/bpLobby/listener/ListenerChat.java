package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class ListenerChat implements Listener {
    @Inject private ConfigurationPlugin configurationPlugin;
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("bplobby.chat")) {
            String color = configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) != null ? configurationPlugin.getFormats().get(PlaceholderAPI.setPlaceholders(player, "%vault_group%")) : "";
            String formattedMessage = managerMessage.getComponent(player, configurationMessage.getEventsChat().getSuccess(), player.getDisplayName(), color, event.getMessage());
            event.setFormat(formattedMessage);
            return;
        }
        event.setCancelled(true);
        managerMessage.sendMessage(player, configurationMessage.getEventsChat().getFailed());
        managerMessage.playSound(player, configurationMessage.getSounds().getError());
    }
}
