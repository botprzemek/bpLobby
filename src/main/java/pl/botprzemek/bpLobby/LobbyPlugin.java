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
import pl.botprzemek.bpLobby.lobby.ManagerEvent;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;
import pl.botprzemek.bpLobby.util.HiddenPlayers;

import java.util.stream.Stream;

public final class LobbyPlugin extends JavaPlugin {
    private final Injector injector = OkaeriInjector.create();
    private ConfigurationPlugin configurationPlugin;
    private ConfigurationMessage configurationMessage;

    @Override
    public void onEnable() {
        this.injector.registerInjectable(this);
        this.injector.registerInjectable(this.injector);
        this.setupConfiguration();
        this.injector.registerInjectable(BukkitAudiences.create(this));
        this.injector.registerInjectable(this.injector.createInstance(ManagerMessage.class));
        this.injector.registerInjectable(this.injector.createInstance(HiddenPlayers.class));

        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
            .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), configurationMessage.getCommandsPlayer().getOffline()))
            .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(configurationMessage.getCommandsPlayer().getOnly()))
            .commandInstance(new CommandVanish(), new CommandReload())
            .invalidUsageHandler(new HandlerInvalid())
            .permissionHandler(new HandlerUnauthorized())
            .register();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "lobby:main");

        Stream.of(
                this.injector.createInstance(ListenerChat.class),
                this.injector.createInstance(ListenerJoinQuit.class),
                this.injector.createInstance(ListenerKick.class),
                this.injector.createInstance(ListenerSpawn.class)
        ).forEach(listener -> Bukkit.getPluginManager().registerEvents(listener, this));
        new ManagerEvent();
    }

    @Override
    public void onDisable() {
        cleanUp();
    }

    private void setupConfiguration() {
        ConfigurationFactory configurationFactory = new ConfigurationFactory(this.getDataFolder());
        this.configurationPlugin = configurationFactory.produce(ConfigurationPlugin.class, "config.yml");
        this.configurationMessage = configurationFactory.produce(ConfigurationMessage.class, "messages.yml");
        this.injector.registerInjectable(this.configurationPlugin);
        this.injector.registerInjectable(this.configurationMessage);
    }

    private void cleanUp() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        configurationPlugin.save();
        configurationMessage.save();
    }
}