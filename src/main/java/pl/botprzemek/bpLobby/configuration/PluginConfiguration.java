package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import pl.botprzemek.bpLobby.configuration.elements.SpawnElement;

@Getter
public class PluginConfiguration extends OkaeriConfig {
    @Comment("Spawn module")
    private SpawnElement spawn = new SpawnElement();
}
