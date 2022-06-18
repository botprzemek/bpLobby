package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.FlyingSpeed;
import pl.botprzemek.methods.PlayerHead;

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
    int fireworkTime = bpLobby.plugin.getConfig().getInt("join-quit.firework.time");
    int titleTime = 20 * bpLobby.plugin.getConfig().getInt("join-quit.title.time");
    int titleFade = 20 * bpLobby.plugin.getConfig().getInt("join-quit.title.fade");

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

        ItemStack item = new PlayerHead().getPlayerHead(player.getName());
        player.getInventory().setItemInMainHand(item);

    }

    // QUIT

    @EventHandler
    public void onPlayerJoin(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(IridiumColorAPI.process(prefix + bye.replace("%player%", player.getName())));

    }
}