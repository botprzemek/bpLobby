package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.lobby.HiddenPlayers;

public class ListenerJoinQuit implements Listener {
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;
    @Inject private HiddenPlayers hiddenPlayers;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(managerMessage.getMessage(player, configurationMessage.getEventsConnect().getJoin(), player.getDisplayName()));
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(managerMessage.getMessage(player, configurationMessage.getEventsConnect().getQuit(), player.getDisplayName()));
        hiddenPlayers.removePlayer(player);
    }
}
