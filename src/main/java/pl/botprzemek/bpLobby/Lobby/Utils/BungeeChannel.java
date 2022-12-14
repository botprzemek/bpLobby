package pl.botprzemek.bpLobby.Lobby.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class BungeeChannel {

    private BpLobby instance;

    private StringSerializer stringSerializer;

    public BungeeChannel(LobbyManager lobbyManager) {

        this.instance = lobbyManager.getInstance();

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    public void sendPlayerToServer(Player player, String server) {

        try {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("Connect");
            out.writeUTF(server);

            player.sendMessage(stringSerializer.serializeServer("server.success", server));

            player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());

        }

        catch (Exception error) {

            player.sendMessage(stringSerializer.serializeServer("server.error", server));

        }
    }
}
