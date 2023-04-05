package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import pl.botprzemek.bpLobby.LobbyPlugin;
import pl.botprzemek.bpLobby.listener.*;

public class ManagerEvent {
    @Inject private LobbyPlugin lobbyPlugin;

    public ManagerEvent() {
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerJoinQuit(), lobbyPlugin);
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerChat(), lobbyPlugin);
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerInventory(), lobbyPlugin);
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerSpawn(), lobbyPlugin);
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerFalling(), lobbyPlugin);
        lobbyPlugin.getServer().getPluginManager().registerEvents(new ListenerKick(), lobbyPlugin);
    }
}
