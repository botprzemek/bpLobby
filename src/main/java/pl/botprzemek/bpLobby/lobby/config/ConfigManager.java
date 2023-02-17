package pl.botprzemek.bpLobby.lobby.config;


import pl.botprzemek.bpLobby.LobbyPlugin;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private final List<Config> configs = new ArrayList<>();

    private final MessageConfig messageConfig;

    private final PluginConfig pluginConfig;

    public ConfigManager(LobbyManager lobbyManager) {

        LobbyPlugin instance = lobbyManager.getInstance();

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
