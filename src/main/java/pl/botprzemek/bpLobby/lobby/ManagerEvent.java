package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.plugin.Plugin;
import pl.botprzemek.bpLobby.LobbyPlugin;
import pl.botprzemek.bpLobby.listener.*;

public class ManagerEvent {
    @Inject private Plugin plugin;

    public ManagerEvent() {
        plugin.getServer().getPluginManager().registerEvents(new ListenerJoinQuit(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ListenerChat(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ListenerSpawn(), plugin);
        plugin.getServer().getPluginManager().registerEvents(new ListenerKick(), plugin);
    }
}
