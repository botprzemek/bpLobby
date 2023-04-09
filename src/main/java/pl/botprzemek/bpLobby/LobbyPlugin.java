package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.OkaeriInjector;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandReload;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.command.CommandVanish;
import pl.botprzemek.bpLobby.configuration.ConfigurationFactory;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.handler.HandlerInvalid;
import pl.botprzemek.bpLobby.handler.HandlerUnauthorized;
import pl.botprzemek.bpLobby.listener.ListenerChat;
import pl.botprzemek.bpLobby.listener.ListenerJoinQuit;
import pl.botprzemek.bpLobby.listener.ListenerKick;
import pl.botprzemek.bpLobby.listener.ListenerSpawn;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.HiddenPlayers;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.stream.Stream;

public final class LobbyPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;
    private ManagerMessage managerMessage;

    @Override
    public void onEnable() {
        injector.registerInjectable(this);
        injector.registerInjectable(injector);
        injector.registerInjectable(BukkitAudiences.create(this));
        injector.registerInjectable(injector.createInstance(HiddenPlayers.class));
        setupConfiguration();
        setupUtils();
        setupCommands();
        setupEvents();

        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "config.yml");
        injector.registerInjectable(configurationPlugin);
        configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        injector.registerInjectable(configurationMessage);
    }

    private void setupUtils() {
        managerMessage = injector.createInstance(ManagerMessage.class);
        injector.registerInjectable(managerMessage);
        injector.registerInjectable(injector.createInstance(BungeeChannel.class));
    }

    private void setupCommands() {
        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), managerMessage.getMessage(configurationMessage.getCommandsPlayer().getOffline())))
                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(managerMessage.getMessage(configurationMessage.getCommandsPlayer().getOnly())))
                .commandInstance(
                        injector.createInstance(CommandReload.class),
                        injector.createInstance(CommandServer.class),
                        injector.createInstance(CommandVanish.class)
                )
                .invalidUsageHandler(injector.createInstance(HandlerInvalid.class))
                .permissionHandler(injector.createInstance(HandlerUnauthorized.class))
                .register();
    }

    private void setupEvents() {
        Stream.of(
                injector.createInstance(ListenerChat.class),
                injector.createInstance(ListenerJoinQuit.class),
                injector.createInstance(ListenerKick.class),
                injector.createInstance(ListenerSpawn.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
    }

    private void cleanUp() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        configurationPlugin.save();
        configurationMessage.save();
    }
}