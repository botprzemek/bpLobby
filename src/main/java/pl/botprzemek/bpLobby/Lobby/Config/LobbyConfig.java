package pl.botprzemek.bpLobby.Lobby.Config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class LobbyConfig extends Config {

    public LobbyConfig(BpLobby instance) {

        super(instance, "lobby.yml");

    }

    public String getLobbyString(String lobbyString) {

        return getString(lobbyString);

    }

    public String getSerializedLobbyString(Player player, StringSerializer stringSerializer, String lobbyString) {

        return stringSerializer.serializePlainTextWithPapi(player, getString(lobbyString));

    }

    public Location getLobbyLocation() {

        ConfigurationSection spawnSection = getConfigurationSection("spawn");

        return new Location(
                Bukkit.getServer().getWorld(spawnSection.getString("world")),
                spawnSection.getDouble("x"),
                spawnSection.getDouble("y"),
                spawnSection.getDouble("z"),
                (float) spawnSection.getDouble("yaw"),
                (float) spawnSection.getDouble("pitch")
        );

    }

    public World getLobbyWorld() {

        return Bukkit.getWorld(getConfigurationSection("spawn").getString("world"));

    }

    public Double getLobbyLimit() {

        return getDouble("limit");

    }

    public ConfigurationSection getLobbySound() {

        return getConfigurationSection("sound");

    }

}
