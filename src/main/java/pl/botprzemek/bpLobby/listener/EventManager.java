package pl.botprzemek.bpLobby.listener;

import pl.botprzemek.bpLobby.LobbyPlugin;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

public class EventManager {
    public EventManager(LobbyManager lobbyManager) {
        LobbyPlugin instance = lobbyManager.getInstance();
        instance.getServer().getPluginManager().registerEvents(new JoinQuitEvent(lobbyManager), instance);
        instance.getServer().getPluginManager().registerEvents(new ChatEvent(), instance);
        instance.getServer().getPluginManager().registerEvents(new InventoryEvent(lobbyManager), instance);
        instance.getServer().getPluginManager().registerEvents(new SpawnProtectionEvent(lobbyManager), instance);
        instance.getServer().getPluginManager().registerEvents(new PlayerFallingEvent(lobbyManager), instance);
        instance.getServer().getPluginManager().registerEvents(new KickWithReasonEvent(lobbyManager), instance);
    }
}
