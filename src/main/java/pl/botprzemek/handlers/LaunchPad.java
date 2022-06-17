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

public class LaunchPad implements Listener {

    public LaunchPad(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    float launchPadPower = bpLobby.plugin.getConfig().getInt("launch-pad.power");
    int launchPadMax = bpLobby.plugin.getConfig().getInt("launch-pad.max");
    int wait = 0;
    Boolean launchPadEnable = bpLobby.plugin.getConfig().getBoolean("launch-pad.enable");
    Material launchPadActivator = Material.valueOf(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("launch-pad.activator")).toUpperCase().replace(" ", "_"));
    Material launchPadMaterial = Material.valueOf(Objects.requireNonNull(bpLobby.plugin.getConfig().getString("launch-pad.material")).toUpperCase().replace(" ", "_"));
    String prefix = bpLobby.plugin.getConfig().getString("prefix");
    String launchPadMessage = bpLobby.plugin.getConfig().getString("messages.launch-pad.activate");
    String sound = Objects.requireNonNull(bpLobby.plugin.getConfig().getString("launch-pad.sound")).toUpperCase().replace(' ', '_');

    @EventHandler
    public void onWalking(PlayerMoveEvent event){
        if(!launchPadEnable){
            return;
        }
        else{
            Player player = event.getPlayer();
            Location location = player.getLocation();
            location.setY(location.getBlockY() - 1);

            if(player.getLocation().getBlock().getType().equals(launchPadActivator) && location.getBlock().getType().equals(launchPadMaterial)){
                if(wait == 0) {
                    player.setVelocity(location.getDirection().multiply(launchPadPower).setY(launchPadMax));
                    player.sendMessage(IridiumColorAPI.process(prefix + launchPadMessage));
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                    ++wait;
                }
                Bukkit.getScheduler().runTaskLater(bpLobby.plugin, () -> { wait = 0; }, 10L);
            }
        }
    }
}
