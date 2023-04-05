package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class PluginConfiguration extends OkaeriConfig {
    @Comment("")
    private SpawnElement spawnElement = new SpawnElement();

    @Getter
    private class SpawnElement extends OkaeriConfig {
        private Location spawnLocation;
        private int limit;
    }
}
