package pl.botprzemek.bpLobby;

import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public final class BpLobby extends JavaPlugin {

    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {

        this.lobbyManager = new LobbyManager(this);

    }

    public void onDisable() {

        lobbyManager.cleanUp();

    }

}