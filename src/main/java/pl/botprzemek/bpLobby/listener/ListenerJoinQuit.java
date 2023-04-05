package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.lobby.ManagerPlugin;

public class ListenerJoinQuit implements Listener {
    @Inject private ManagerPlugin managerPlugin;
    @Inject private ManagerMessage managerMessage;

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage(managerMessage.getStringMessage(player, "events.connect.join", String.valueOf(Bukkit.getOnlinePlayers().size())));

        if (player.getGameMode().equals(GameMode.SURVIVAL)) player.getInventory().clear();

        player.getInventory().setHeldItemSlot(4);
        managerPlugin.setNewHiddenPlayer(player);
        managerPlugin.setPlayerSelectorItem(player);
        managerPlugin.setInventory(player);
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage(managerMessage.getStringMessage(player, "events.connect.quit", String.valueOf(Bukkit.getOnlinePlayers().size() - 1)));
        managerPlugin.clearHiddenPlayer(player);
        managerPlugin.removeInventory(player);
    }
}
