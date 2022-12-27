package pl.botprzemek.bpLobby.Command;

import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class CommandManager {

    public CommandManager(LobbyManager lobbyManager){

        BpLobby instance = lobbyManager.getInstance();

        instance.getCommand("bplobby").setExecutor(new ReloadCommand(lobbyManager));

        instance.getCommand("players").setExecutor(new PlayersCommand(lobbyManager));

        instance.getCommand("server").setExecutor(new ServerCommand(lobbyManager));

    }

}
