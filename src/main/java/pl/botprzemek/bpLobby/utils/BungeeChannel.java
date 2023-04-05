package pl.botprzemek.bpLobby.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.LobbyPlugin;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class BungeeChannel {
    @Inject
    ManagerMessage managerMessage;
    @Inject LobbyPlugin lobbyPlugin;

    public void sendPlayer(Player player, String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());
            managerMessage.sendCommandMessage(player, "server.success", server);
            managerMessage.playSound(player, "activate");
            player.sendPluginMessage(lobbyPlugin, "lobby", out.toByteArray());
        }
        catch (Exception error) {
            managerMessage.sendCommandMessage(player, "server.error", server);
            managerMessage.playSound(player, "error");
        }
    }
}
