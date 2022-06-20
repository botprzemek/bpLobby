package pl.botprzemek.scrap;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.botprzemek.bpLobby;

import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class SpikesTrap implements Listener {

    public SpikesTrap(bpLobby plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    double spikesTrapPower = plugin.getConfig().getDouble("spikes-trap.power");
    int wait = 0;
    int spikesTrapEffectDuration = 20 * plugin.getConfig().getInt("spikes-trap.effect.duration");
    int spikesTrapEffectStrength = plugin.getConfig().getInt("spikes-trap.effect.strength") - 1;
    Boolean spikesTrapEnable = plugin.getConfig().getBoolean("spikes-trap.enable");
    Boolean spikesTrapEffectEnable = plugin.getConfig().getBoolean("spikes-trap.effect.enable");
    Material spikesTrapActivator = Material.valueOf(Objects.requireNonNull(plugin.getConfig().getString("spikes-trap.activator")).toUpperCase().replace(" ", "_"));
    PotionEffectType spikesTrapEffect = PotionEffectType.getByName(Objects.requireNonNull(plugin.getConfig().getString("spikes-trap.effect.effect")));
    String prefix = plugin.getConfig().getString("prefix");
    String spikesTrapMessage = plugin.getConfig().getString("messages.spikes-trap.activate");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("spikes-trap.sound")).toUpperCase().replace(' ', '_');

    @EventHandler
    public void onWalking(PlayerMoveEvent event){
        if(!spikesTrapEnable){
            return;
        }
        else if(wait == 1){
            return;
        }
        else{
            Player player = event.getPlayer();

            if(player.getGameMode() != GameMode.SURVIVAL){
                return;
            }

            if(player.getLocation().getBlock().getType().equals(spikesTrapActivator)){

                if(wait == 0) {

                    player.damage(spikesTrapPower);
                    player.sendMessage(IridiumColorAPI.process(prefix + spikesTrapMessage));
                    if(spikesTrapEffectEnable) player.addPotionEffect(new PotionEffect(spikesTrapEffect, spikesTrapEffectDuration, spikesTrapEffectStrength));
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                    ++wait;

                }

                Bukkit.getScheduler().runTaskLater(plugin, () -> { wait = 0; }, 100L);
            }
        }
    }
}
