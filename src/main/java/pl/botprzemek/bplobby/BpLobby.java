package pl.botprzemek.bplobby;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bplobby.commands.RegisterCommand;
import pl.botprzemek.bplobby.events.RegisterEvent;
import pl.botprzemek.bplobby.utils.ConsoleStartup;

import java.io.File;
import java.io.IOException;

public final class BpLobby extends JavaPlugin {

    private static BpLobby instance;
    private BukkitAudiences adventure;

    private File pluginConfigFile;

    private static YamlConfiguration pluginConfig;

    private static MiniMessage mm = MiniMessage.miniMessage();

    public BukkitAudiences adventure() {

        if(this.adventure == null) {

            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");

        }

        return this.adventure;

    }

    public static FileConfiguration getInstanceConfig(){

        return pluginConfig;

    }

    public void loadInstanceConfig(){

        pluginConfigFile = new File(getDataFolder(), "config.yml");

        pluginConfig = new YamlConfiguration();

        try {

            pluginConfig.load(pluginConfigFile);

        }

        catch (IOException | InvalidConfigurationException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void onEnable() {

        instance = this;

        loadInstanceConfig();

        adventure = BukkitAudiences.create(this);

        new ConsoleStartup(adventure, this);

        new RegisterCommand(this);

        new RegisterEvent(this);

    }

    public void onDisable() {

        if(this.adventure != null) {

            this.adventure.close();
            this.adventure = null;

        }

    }

    public static BpLobby getInstance() {
        return instance;
    }

    public static MiniMessage getMiniMessage() {
        return mm;
    }

}