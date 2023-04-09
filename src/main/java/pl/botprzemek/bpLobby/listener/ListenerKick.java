package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class ListenerKick implements Listener {
    @Inject
    private ConfigurationMessage configurationMessage;
    @Inject
    private ManagerMessage managerMessage;

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        if (event.getPlayer().hasPermission("bplobby.bypass")) event.setCancelled(true);
        if (!event.getReason().equals("You have been idle for too long!")) return;
        event.setReason(managerMessage.getComponent(event.getPlayer(), configurationMessage.getEventsDisonnect().getTimeout()));
    }
}
