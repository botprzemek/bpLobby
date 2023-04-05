package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {
    @Comment("Spawn")
    private Location spawnLocation = null;
    @Comment("Limit")
    private int limit = 45;
}
