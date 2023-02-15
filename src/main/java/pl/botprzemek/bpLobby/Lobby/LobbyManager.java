package pl.botprzemek.bpLobby.Lobby;

import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Command.CommandManager;
import pl.botprzemek.bpLobby.Event.EventManager;
import pl.botprzemek.bpLobby.Lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.Lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Config.PluginManager;
import pl.botprzemek.bpLobby.Lobby.Utils.*;

public class LobbyManager {

    private final BpLobby instance;

    private final ConfigManager configManager;

    private final BungeeChannel bungeeChannel;

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

//    private final ServerSelector serverSelector;

    public LobbyManager(BpLobby instance) {

        this.instance = instance;

        configManager = new ConfigManager(this);

        messageManager = new MessageManager(this);

//        serverSelector = new ServerSelector(this);

        bungeeChannel = new BungeeChannel(this);

        pluginManager = new PluginManager(this);

        new CommandManager(this);

        new EventManager(this);

    }

    public void cleanUp() {

        instance.getServer().getMessenger().unregisterOutgoingPluginChannel(instance);

        configManager.saveConfigs();

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

    public MessageManager getMessageManager() {

        return messageManager;

    }

    public PluginManager getPluginManager() {

        return pluginManager;

    }

//    public ServerSelector getServerSelector() {
//
//        return serverSelector;
//
//    }

}
