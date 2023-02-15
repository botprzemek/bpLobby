package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.configuration.ConfigurationFactory;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.Utils.Serializer;

public final class BpLobby extends JavaPlugin {
    private LobbyManager lobbyManager;
    private final Injector injector = OkaeriInjector.create();
    private PluginConfiguration configuration;


    @Override
    public void onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);

        this.setupConfiguration();

        this.injector.registerInjectable(this.injector.createInstance(MessageManager.class));
        this.injector.registerInjectable(new Serializer());

        this.lobbyManager = new LobbyManager(this);
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