package pl.botprzemek.bpLobby;

import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.plugin.java.JavaPlugin;
import pl.botprzemek.bpLobby.command.CommandServer;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

public final class BpLobby extends JavaPlugin {
    private LobbyManager lobbyManager;

    @Override
    public void onEnable() {
        this.lobbyManager = new LobbyManager(this);
        LiteBukkitFactory.builder(this.getServer(), "bpLobby")
            .commandInstance(new CommandServer(lobbyManager))
            .register();
    }

    @Override
    public void onDisable() {
        lobbyManager.cleanUp();
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }
}