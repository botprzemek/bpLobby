package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby;
import pl.botprzemek.methods.FireworkGenerator;
import pl.botprzemek.methods.ParticleGenerator;
import pl.botprzemek.methods.PlayerHead;
import pl.botprzemek.methods.PlayerSee;

import java.util.Objects;
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
    Location spawn = new Location(Bukkit.getWorld(plugin.getConfig().getString("join-quit.spawn.world")), plugin.getConfig().getDouble("join-quit.spawn.x"), plugin.getConfig().getDouble("join-quit.spawn.y"), plugin.getConfig().getDouble("join-quit.spawn.z"), (float) plugin.getConfig().getDouble("join-quit.spawn.pitch"), (float) plugin.getConfig().getDouble("join-quit.spawn.yaw"));
    Color fireworkColor = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.color")).replace("#", "")), 16));
    Color fireworkFade = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.fade")).replace("#", "")), 16));
    int fireworkTime = plugin.getConfig().getInt("join-quit.firework.time");
    int titleTime = 20 * plugin.getConfig().getInt("join-quit.title.time");
    int titleFade = 20 * plugin.getConfig().getInt("join-quit.title.fade");
    Boolean playerHeadEnable = plugin.getConfig().getBoolean("player-head.enabled");
    Boolean fireworkOffset = plugin.getConfig().getBoolean("join-quit.firework.offset");

    FireworkGenerator fireworkGenerator = new FireworkGenerator();
    ParticleGenerator particleGenerator = new ParticleGenerator();
    PlayerHead playerHead = new PlayerHead();
    PlayerSee playerSee = new PlayerSee();

    // JOIN

//    public void hideEntityForPlayer(Player player, Player target) {
//        PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(target);
//        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
//    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if (spawn != null) player.teleport(spawn);
        if (!player.hasPlayedBefore()) player.setBedSpawnLocation(spawn, true);

        event.setJoinMessage(IridiumColorAPI.process(prefix + welcome.replace("%player%", player.getName())));
        player.sendTitle(IridiumColorAPI.process(title), IridiumColorAPI.process(subtitle.replace("%player%", player.getName())), titleFade, titleTime, titleFade);

        if (!fireworkOffset) fireworkGenerator.generateFireworks(event, player, fireworkShape, fireworkColor, fireworkFade, fireworkTime);
        else {
            fireworkGenerator.generateFireworks(event, player, fireworkShape, fireworkColor, fireworkFade, fireworkTime, -2, -3);
            fireworkGenerator.generateFireworks(event, player, fireworkShape, fireworkColor, fireworkFade, fireworkTime, -2, 3);
            fireworkGenerator.generateFireworks(event, player, fireworkShape, fireworkColor, fireworkFade, fireworkTime, -3, 2);
        }

        playerSee.hidePlayersEveryone(player);

        particleGenerator.onSpawnParticle(player);

        if (playerHeadEnable) {

            ItemStack item = playerHead.getPlayerHead(player.getPlayerProfile());
            player.getInventory().setItem(4, item);

        }

        //Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(6);

    }

    // QUIT

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(IridiumColorAPI.process(prefix + bye.replace("%player%", player.getName())));

    }
}