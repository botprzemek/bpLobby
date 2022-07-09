package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import pl.botprzemek.bpLobby;

import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class LaunchPad implements Listener {

    public LaunchPad(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    float launchPadPower = plugin.getConfig().getInt("launch-pad.power");
    float launchPadPitch = Float.parseFloat(Objects.requireNonNull(plugin.getConfig().getString("launch-pad.pitch")).replace(",", "."));
    int launchPadMax = plugin.getConfig().getInt("launch-pad.max");
    int wait = 0;
    Material launchPadActivator = Material.valueOf(Objects.requireNonNull(plugin.getConfig().getString("launch-pad.activator")).toUpperCase().replace(" ", "_"));
    Material launchPadMaterial = Material.valueOf(Objects.requireNonNull(plugin.getConfig().getString("launch-pad.material")).toUpperCase().replace(" ", "_"));
    String prefix = plugin.getConfig().getString("prefix");
    String launchPadMessage = plugin.getConfig().getString("messages.launch-pad.activate");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("launch-pad.sound")).toUpperCase().replace(' ', '_');

    @EventHandler
    public void onWalking(PlayerMoveEvent event){

            Player player = event.getPlayer();
            Location location = player.getLocation();
            location.setY(location.getBlockY() - 2);

//          if(!(player.getLocation().getBlock().getType().equals(launchPadActivator) && location.getBlock().getType().equals(launchPadMaterial))) return;
            if(!(location.getBlock().getType().equals(launchPadMaterial))) return;

            if(wait == 0) {

                plugin.jumpingPlayers.add(player);
                player.setVelocity(location.getDirection().multiply(launchPadPower).setY(launchPadMax));
                player.sendMessage(IridiumColorAPI.process(prefix + launchPadMessage));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, launchPadPitch);
                ++wait;

            }
            Bukkit.getScheduler().runTaskLater(plugin, () -> {

                wait = 0;

            }, 5L);
    }
}
