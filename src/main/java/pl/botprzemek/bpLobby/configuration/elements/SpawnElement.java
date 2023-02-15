package pl.botprzemek.bpLobby.configuration.elements;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Getter;
import org.bukkit.Location;

@Getter
public class SpawnElement extends OkaeriConfig {
    private Location spawnLocation;
    private int limit;
}
