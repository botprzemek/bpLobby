package pl.botprzemek.bpLobby.listener;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.botprzemek.bpLobby.lobby.ManagerPlugin;

public class ListenerFalling implements Listener {
    @Inject private ManagerPlugin managerPlugin;

    @EventHandler
    public void onPlayerFall(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.hasPermission("bplobby.bypass")) return;
        if (player.getLocation().getY() > managerPlugin.getLimit()) return;

        player.teleport(managerPlugin.getSpawnLocation());
        managerPlugin.createCustomElements(player);
    }

    @EventHandler
    public void onPlayerDeath(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        event.setRespawnLocation(managerPlugin.getSpawnLocation());
        managerPlugin.createCustomElements(player);
        managerPlugin.setPlayerSelectorItem(player);
    }
}
