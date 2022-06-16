package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby;

public class JoinQuitHandler implements Listener {

    public JoinQuitHandler(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    // JOIN

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String prefix = bpLobby.plugin.getConfig().getString("prefix");
        String welcome = bpLobby.plugin.getConfig().getString("messages.welcome");

        assert welcome != null;
        event.setJoinMessage(IridiumColorAPI.process(prefix + welcome.replace("%player%", player.getName())));
        player.sendTitle("Hello!", "This is a test.", 1, 20, 1);

    }

    // QUIT

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        String prefix = bpLobby.plugin.getConfig().getString("prefix");
        String bye = bpLobby.plugin.getConfig().getString("messages.bye");

        assert bye != null;
        event.setQuitMessage(IridiumColorAPI.process(prefix + bye.replace("%player%", player.getName())));

    }
}