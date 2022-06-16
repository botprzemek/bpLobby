package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.botprzemek.bpLobby;

import java.util.Objects;

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
        String title = bpLobby.plugin.getConfig().getString("messages.title.title");
        String subtitle = bpLobby.plugin.getConfig().getString("messages.title.subtitle");
        int titleTime = 20 * Integer.parseInt(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("messages.title.time")));
        int titleFade = 20 * Integer.parseInt(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("messages.title.fade")));

        assert prefix != null;
        assert title != null;
        assert subtitle != null;
        assert welcome != null;
        event.setJoinMessage(IridiumColorAPI.process(prefix + welcome.replace("%player%", player.getName())));
        player.sendTitle(IridiumColorAPI.process(title), IridiumColorAPI.process(subtitle.replace("%player%", player.getName())), titleFade, titleTime, titleFade);

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