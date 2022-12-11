package pl.botprzemek.bpLobby.Lobby;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Command.CommandManager;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Event.EventManager;
import pl.botprzemek.bpLobby.Utils.ConsoleStartup;
import pl.botprzemek.bpLobby.Utils.StringSerializer;

public class LobbyManager {

    private BpLobby instance;

    private ConfigManager configManager;

    private StringSerializer stringSerializer;

    private BukkitAudiences adventure;

    public LobbyManager(BpLobby instance) {

        this.instance = instance;

        this.adventure = BukkitAudiences.create(instance);

        this.configManager = new ConfigManager(this);

        this.stringSerializer = new StringSerializer(this);

        configManager.loadConfigs();

        new ConsoleStartup(this);

        new CommandManager(this);

        new EventManager(this);

    }

    public void cleanUp() {

        if (adventure != null) adventure.close();

    }

    public BpLobby getInstance() {

        return instance;

    }

    public ConfigManager getConfigManager() {

        return configManager;

    }

    public BukkitAudiences getAdventure() {

        return adventure;

    }

    public StringSerializer getStringSerializer() {

        return stringSerializer;

    }

}
