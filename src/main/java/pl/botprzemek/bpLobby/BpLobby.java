package pl.botprzemek.bpLobby;

import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public final class BpLobby extends JavaPlugin {

    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {

        this.lobbyManager = new LobbyManager(this);

    }

    @Override
    public void onDisable() {

        lobbyManager.cleanUp();

    }

    public LobbyManager getLobbyManager() {

        return lobbyManager;

    }

}