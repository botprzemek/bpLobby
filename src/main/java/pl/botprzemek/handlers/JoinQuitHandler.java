package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bpLobby;

public class JoinQuitHandler implements Listener {

    public JoinQuitHandler(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // JOIN

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        event.setJoinMessage(IridiumColorAPI.process(bpLobby.plugin.getConfig().getString("prefix") + player.getDisplayName() + bpLobby.plugin.getConfig().getString("messages.welcome")));

    }

    // QUIT

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        event.setQuitMessage(IridiumColorAPI.process(bpLobby.plugin.getConfig().getString("prefix") + player.getDisplayName() + bpLobby.plugin.getConfig().getString("messages.bye")));


    }
}