package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import pl.botprzemek.bpLobby;

import java.util.Objects;

public class JoinQuit implements Listener {

    public JoinQuit(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    String prefix = bpLobby.plugin.getConfig().getString("prefix");
    String welcome = bpLobby.plugin.getConfig().getString("join-quit.welcome");
    String bye = bpLobby.plugin.getConfig().getString("join-quit.bye");
    String title = bpLobby.plugin.getConfig().getString("join-quit.title.title");
    String subtitle = bpLobby.plugin.getConfig().getString("join-quit.title.subtitle");
    String fireworkShape = bpLobby.plugin.getConfig().getString("join-quit.firework.shape");
    Color fireworkColor = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("join-quit.firework.color")).replace("#", "")), 16));
    Color fireworkFade = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("join-quit.firework.fade")).replace("#", "")), 16));
    int fireworkTime = Integer.parseInt(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("join-quit.firework.time")));
    int titleTime = 20 * Integer.parseInt(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("join-quit.title.time")));
    int titleFade = 20 * Integer.parseInt(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("join-quit.title.fade")));

    // JOIN

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(IridiumColorAPI.process(prefix + welcome.replace("%player%", player.getName())));
        player.sendTitle(IridiumColorAPI.process(title), IridiumColorAPI.process(subtitle.replace("%player%", player.getName())), titleFade, titleTime, titleFade);

        Firework firework = player.getWorld().spawn(event.getPlayer().getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(true)
                .with(FireworkEffect.Type.valueOf(fireworkShape.toUpperCase()))
                .withColor(fireworkColor)
                .withFade(fireworkFade)
                .build());
        fireworkMeta.setPower(fireworkTime);
        firework.setFireworkMeta(fireworkMeta);

    }

    // QUIT

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(IridiumColorAPI.process(prefix + bye.replace("%player%", player.getName())));

    }
}