package pl.botprzemek.bpLobby.Event;

import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class EventManager {

    public EventManager(LobbyManager lobbyManager) {

        BpLobby instance = lobbyManager.getInstance();

        instance.getServer().getPluginManager().registerEvents(new JoinQuitEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new ChatEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new InventoryEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new BlockInteractionEvent(lobbyManager), instance);

        instance.getServer().getPluginManager().registerEvents(new PlayerFallingEvent(lobbyManager), instance);

    }
}
