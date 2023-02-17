package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandPlayers;
import pl.botprzemek.bpLobby.command.CommandReload;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.configuration.ConfigurationFactory;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;

public final class LobbyPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    private PluginConfiguration configuration;
    private BukkitAudiences audiences;


    @Override
    public void onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);

        this.setupConfiguration();
        this.audiences = BukkitAudiences.create(this);

        this.injector.registerInjectable(this.injector.createInstance(MessageManager.class));
        this.injector.registerInjectable(new Serializer); // co jest kurwa czemu to nie dziala XD
        this.injector.registerInjectable(this.audiences);

        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
            .command(CommandPlayers.class, CommandServer.class, CommandReload.class)
            .permissionHandler(null) // TODO
            .register();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        this.configuration = configurationFactory.produce(PluginConfiguration.class, "configuration.yml");
        this.injector.registerInjectable(this.configuration);
    }

    private void cleanUp() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        configuration.save();
    }
}