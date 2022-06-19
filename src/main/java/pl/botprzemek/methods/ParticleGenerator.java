package pl.botprzemek.methods;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static pl.botprzemek.bpLobby.plugin;

public class ParticleGenerator {

    Color fireworkColor = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.color")).replace("#", "")), 16));
    Color fireworkFade = Color.fromRGB(Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(plugin.getConfig().getString("join-quit.firework.fade")).replace("#", "")), 16));

    public void onSpawnParticle(Player player) {

        Particle.DustTransition dustTransition = new Particle.DustTransition(fireworkColor, fireworkFade, 0.75F);

            new BukkitRunnable(){
            Location loc = player.getLocation();
            double t = 0;
            public void run(){
                t += Math.PI/16;
                double x = sin(t+Math.PI)/1.5;
                double y = t*0.13;
                double z = cos(t+Math.PI)/1.5;
                loc.add(x, y, z);
                player.spawnParticle(Particle.DUST_COLOR_TRANSITION, loc, 0, 0, -0.3, 0, dustTransition);
                player.spawnParticle(Particle.END_ROD, loc, 0, 0, 0.2, 0);
                loc.subtract(x, y, z);
                if (t > Math.PI*3){
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);

    }

    public void onFallParticle(Player player) {

        new BukkitRunnable(){
            Location loc = player.getLocation();
            double t = 0;
            public void run(){
                t += Math.PI/16;
                double x = sin(t+Math.PI)/1.5;
                double y = t*0.3;
                double z = cos(t+Math.PI)/1.5;
                loc.add(x, y, z);
                player.spawnParticle(Particle.LAVA, loc, 0, 0, 0, 0);
                loc.subtract(x, y, z);
                if (t > Math.PI*2){
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 1);

    }

}
