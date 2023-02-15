package pl.botprzemek.bpLobby.event;

import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

public class EventManager {

    public EventManager(LobbyManager lobbyManager) {

        BpLobby instance = lobbyManager.getInstance();

        instance.getServer().getPluginManager().registerEvents(new JoinQuitEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new ChatEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new InventoryEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new SpawnProtectionEvent(lobbyManager), instance);

        if (lobbyManager.getConfigManager().getLobbyConfig().getLobbyLimit() != 0) instance.getServer().getPluginManager().registerEvents(new PlayerFallingEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new KickWithReasonEvent(lobbyManager), instance);

    }
}
