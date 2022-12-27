package pl.botprzemek.bpLobby.Lobby.Config;


import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private BpLobby instance;

    private List<Config> configs = new ArrayList<>();

    private MessageConfig messageConfig;

    private InventoryConfig inventoryConfig;

    private LobbyConfig lobbyConfig;

    public ConfigManager(LobbyManager lobbyManager) {

        this.instance = lobbyManager.getInstance();

        configs.add(this.messageConfig = new MessageConfig(instance));

        configs.add(this.inventoryConfig = new InventoryConfig(instance));

        configs.add(this.lobbyConfig = new LobbyConfig(instance));

    }

    public void loadConfigs() {

        instance.getLogger().info("Loading configs...");

        for (Config config : configs) {

            config.loadConfig();

        }

    }

    public void saveConfigs() {

        instance.getLogger().info("Saving configs...");

        for (Config config : configs) {

            config.saveConfig();

        }

    }

    public MessageConfig getMessageConfig() {

        return messageConfig;

    }

    public InventoryConfig getInventoryConfig() {

        return inventoryConfig;

    }

    public LobbyConfig getLobbyConfig() {

        return lobbyConfig;

    }

}
