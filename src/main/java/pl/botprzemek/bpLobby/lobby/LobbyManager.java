package pl.botprzemek.bpLobby.lobby;

import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.event.EventManager;
import pl.botprzemek.bpLobby.lobby.Config.ConfigManager;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.Config.PluginManager;
import pl.botprzemek.bpLobby.lobby.Utils.BungeeChannel;

public class LobbyManager {
    private final BpLobby instance;
    private final ConfigManager configManager;
    private final BungeeChannel bungeeChannel;
    private final MessageManager messageManager;
    private final PluginManager pluginManager;

    public LobbyManager(BpLobby instance) {
        this.instance = instance;
        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);
        bungeeChannel = new BungeeChannel(this);
        pluginManager = new PluginManager(this);

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
}
