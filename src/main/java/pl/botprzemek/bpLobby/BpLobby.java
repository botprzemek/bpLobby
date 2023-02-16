package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.configuration.ConfigurationFactory;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;
import pl.botprzemek.bpLobby.lobby.util.Serializer;

public final class BpLobby extends JavaPlugin {
    private LobbyManager lobbyManager;
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
        this.injector.registerInjectable(new Serializer());
        this.injector.registerInjectable(this.audiences);

        this.lobbyManager = new LobbyManager();

        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
            .commandInstance(new CommandServer(lobbyManager))
            .register();
    }

    @Override
    public void onDisable() {
        lobbyManager.cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        this.configuration = configurationFactory.produce(PluginConfiguration.class, "configuration.yml");
        this.injector.registerInjectable(this.configuration);
    }
}