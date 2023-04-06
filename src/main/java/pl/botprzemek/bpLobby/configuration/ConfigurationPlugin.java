package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {
    @Comment("Spawn")
    private Spawn spawn = new Spawn();
    @Comment("Limit")
    private int limit = 45;

    @Getter
    public class Spawn extends OkaeriConfig {
        private World world = Bukkit.getWorld("world");
        private Double x = 0.5;
        private Double y = 52.0;
        private Double z = 0.5;
        private Float yaw = (float) 0.0;
        private Float pitch = (float) -5.0;
        private Location location = new Location(world, x, y, z, yaw, pitch);
    }
}
