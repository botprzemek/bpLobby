package pl.botprzemek.bpLobby.Lobby.Utils;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Config.LobbyConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class EventCustomization {

    private BpLobby instance;

    private LobbyConfig lobbyConfig;

    public EventCustomization(LobbyManager lobbyManager) {

        this.instance = lobbyManager.getInstance();

        this.lobbyConfig = lobbyManager.getConfigManager().getLobbyConfig();

    }

    public void createCustomElements(Player player) {

        Location playerlocation = player.getLocation();

        player.playSound(playerlocation,
                Sound.valueOf(lobbyConfig.getLobbySound().getString("name").toUpperCase()),
                (float) lobbyConfig.getLobbySound().getDouble("volume"), (float) lobbyConfig.getLobbySound().getDouble("pitch"));

        ConfigurationSection particle = lobbyConfig.getConfigurationSection("particle");

        Particle.DustTransition dustTransition = new Particle.DustTransition(
                Color.fromRGB(Integer.parseInt(particle.getString("color1").replace("#", ""), 16)),
                Color.fromRGB(Integer.parseInt(particle.getString("color2").replace("#", ""), 16)), (float) particle.getDouble("size"));

        new BukkitRunnable(){
            double t = 0;

            public void run(){
                Location location = player.getLocation();
                location.setY(location.getY() + t/2);

                double size = particle.getDouble("radius");

                size += t;

                t += 0.05;

                for (int d = 0; d <= 16 * particle.getInt("amount"); d += 1) {
                    Location particleLoc = new Location(location.getWorld(), location.getX(), location.getY()+particle.getDouble("y-offset"), location.getZ());
                    particleLoc.setX(location.getX() + Math.cos(d) * size);
                    particleLoc.setZ(location.getZ() + Math.sin(d) * size);
                    player.spawnParticle(Particle.valueOf(particle.getString("name").toUpperCase()), particleLoc, particle.getInt("amount"), dustTransition);
                }
                if (t > particle.getInt("time")) {
                    this.cancel();
                }
            }
        }.runTaskTimer(instance, 0, particle.getInt("speed"));
    }

}
