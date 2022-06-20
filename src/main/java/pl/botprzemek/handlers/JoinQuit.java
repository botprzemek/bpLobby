package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.FireworkGenerator;
import pl.botprzemek.methods.ParticleGenerator;
import pl.botprzemek.methods.PlayerHead;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static pl.botprzemek.bpLobby.plugin;

public class JoinQuit implements Listener {

    public JoinQuit(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    String prefix = plugin.getConfig().getString("prefix");
    String welcome = plugin.getConfig().getString("join-quit.welcome");
    String bye = plugin.getConfig().getString("join-quit.bye");
    String title = plugin.getConfig().getString("join-quit.title.title");
    String subtitle = plugin.getConfig().getString("join-quit.title.subtitle");
    String fireworkShape = plugin.getConfig().getString("join-quit.firework.shape");
    Color fireworkColor = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.color")).replace("#", "")), 16));
    Color fireworkFade = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.fade")).replace("#", "")), 16));
    int fireworkTime = plugin.getConfig().getInt("join-quit.firework.time");
    int titleTime = 20 * plugin.getConfig().getInt("join-quit.title.time");
    int titleFade = 20 * plugin.getConfig().getInt("join-quit.title.fade");

    FireworkGenerator fireworkGenerator = new FireworkGenerator();
    ParticleGenerator particleGenerator = new ParticleGenerator();
    PlayerHead playerHead = new PlayerHead();

    // JOIN

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(IridiumColorAPI.process(prefix + welcome.replace("%player%", player.getName())));
        player.sendTitle(IridiumColorAPI.process(title), IridiumColorAPI.process(subtitle.replace("%player%", player.getName())), titleFade, titleTime, titleFade);

        fireworkGenerator.generateFireworks(event, player, fireworkShape, fireworkColor, fireworkFade, fireworkTime);
        particleGenerator.onSpawnParticle(player);

        ItemStack item = playerHead.getPlayerHead(player.getName());

        player.getInventory().setItem(4, item);

    }

    // QUIT

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(IridiumColorAPI.process(prefix + bye.replace("%player%", player.getName())));

    }
}