package pl.botprzemek.bpLobby.Lobby;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Command.CommandManager;
import pl.botprzemek.bpLobby.Event.EventManager;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;
import pl.botprzemek.bpLobby.Lobby.Utils.ConsoleStartup;
import pl.botprzemek.bpLobby.Lobby.Utils.HideShowPlayers;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

public class LobbyManager {

    private BpLobby instance;

    private ConfigManager configManager;

    private BungeeChannel bungeeChannel;

    private StringSerializer stringSerializer;

    private HideShowPlayers hideShowPlayers;

    private BukkitAudiences adventure;

    private ServerSelector serverSelector;

    public LobbyManager(BpLobby instance) {

        this.instance = instance;

        instance.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");

        this.configManager = new ConfigManager(this);

        configManager.loadConfigs();

        this.adventure = BukkitAudiences.create(instance);

        this.stringSerializer = new StringSerializer(this);

        this.bungeeChannel = new BungeeChannel(this);

        this.hideShowPlayers = new HideShowPlayers(instance);

        this.serverSelector = new ServerSelector(this, "server-selector");

        new ConsoleStartup(this);

        new CommandManager(this);

        new EventManager(this);

    }

    public void cleanUp() {

        if (adventure != null) adventure.close();

        instance.getServer().getMessenger().unregisterOutgoingPluginChannel(instance);

    }

    public BpLobby getInstance() {

        return instance;

    }

    public ConfigManager getConfigManager() {

        return configManager;

    }

    public BungeeChannel getBungeeChannel() {

        return bungeeChannel;

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
