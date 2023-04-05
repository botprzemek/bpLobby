package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandReload;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.command.CommandVanish;
import pl.botprzemek.bpLobby.configuration.ConfigurationFactory;
import pl.botprzemek.bpLobby.configuration.MessageConfiguration;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.ManagerEvent;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.utils.Serializer;

public final class LobbyPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    private PluginConfiguration pluginConfiguration;
    private MessageConfiguration messageConfiguration;

    @Override
    public void onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);
        this.setupConfiguration();

        this.injector.registerInjectable(BukkitAudiences.create(this));
        this.injector.registerInjectable(new Serializer());
        this.injector.registerInjectable(this.injector.createInstance(ManagerMessage.class));

        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
            .commandInstance(new CommandVanish(), new CommandServer(), new CommandReload())
            .permissionHandler(null) // TODO
            .register();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "lobby");
        new ManagerEvent();
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        this.pluginConfiguration = configurationFactory.produce(PluginConfiguration.class, "config.yml");
        this.messageConfiguration = configurationFactory.produce(MessageConfiguration.class, "messages.yml");
        this.injector.registerInjectable(this.pluginConfiguration);
        this.injector.registerInjectable(this.messageConfiguration);
    }

    private void cleanUp() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        pluginConfiguration.save();
        messageConfiguration.save();
    }
}