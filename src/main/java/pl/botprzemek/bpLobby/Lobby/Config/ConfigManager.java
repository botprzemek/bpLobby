package pl.botprzemek.bpLobby.Lobby.Config;


import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final List<Config> configs = new ArrayList<>();

    private final MessageConfig messageConfig;

    private final PluginConfig pluginConfig;

    public ConfigManager(LobbyManager lobbyManager) {

        BpLobby instance = lobbyManager.getInstance();

        configs.add(messageConfig = new MessageConfig(instance, "messages.yml"));

        configs.add(pluginConfig = new PluginConfig(instance, "config.yml"));

        loadConfigs();

    }

    public void loadConfigs() {

        for (Config config : configs) config.loadConfig();

    }

    public void saveConfigs() {

        for (Config config : configs) config.saveConfig();

    }

    public MessageConfig getMessageConfig() {

        return messageConfig;

    }

    public PluginConfig getPluginConfig() {

        return pluginConfig;

    }

}
