package pl.botprzemek.bpLobby.Lobby.Config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import pl.botprzemek.bpLobby.BpLobby;

import java.io.File;
import java.io.IOException;

public abstract class Config extends YamlConfiguration {

    protected BpLobby instance;

    protected String name;

    protected File file;

    public Config(BpLobby instance, String name) {

        this.instance = instance;

        this.name = name;

        file = new File(instance.getDataFolder(), name);

    }

    private void checkConfig() {

        if (file.exists()) return;

        file.getParentFile().mkdirs();

        instance.saveResource(name, false);

    }

    public void loadConfig() {

        checkConfig();

        try {

            load(file);

        }

        catch (InvalidConfigurationException | IOException exception) {

            exception.printStackTrace();

        }

    }

    public void saveConfig() {

        try {

            save(file);

        }

        catch (IOException exception) {

            exception.printStackTrace();

        }

    }

}
