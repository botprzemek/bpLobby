package pl.botprzemek.bpLobby.Lobby;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Command.CommandManager;
import pl.botprzemek.bpLobby.Event.EventManager;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.Utils.ConsoleStartup;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class LobbyManager {

    private BpLobby instance;

    private ConfigManager configManager;

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    private BukkitAudiences adventure;

    private ServerSelector serverSelector;

    public LobbyManager(BpLobby instance) {

        this.instance = instance;

        this.adventure = BukkitAudiences.create(instance);

        this.hideShowPlayers = new HideShowPlayers(instance);

        this.configManager = new ConfigManager(this);

        this.stringSerializer = new StringSerializer(this);

        configManager.loadConfigs();

        this.serverSelector = new ServerSelector(this);

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

    public HideShowPlayers getHideShowPlayers() {

        return hideShowPlayers;

    }

    public StringSerializer getStringSerializer() {

        return stringSerializer;

    }

    public ServerSelector getServerSelector() {

        return serverSelector;

    }

}
