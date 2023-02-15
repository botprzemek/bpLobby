package pl.botprzemek.bpLobby.lobby.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.lobby.LobbyManager;

public class BungeeChannel {

    private final BpLobby instance;

    private final MessageManager messageManager;

    public BungeeChannel(LobbyManager lobbyManager) {

        instance = lobbyManager.getInstance();

        instance.getServer().getMessenger().registerOutgoingPluginChannel(instance, "BungeeCord");

        messageManager = lobbyManager.getMessageManager();

    }

    public void sendPlayerToServer(Player player, String server) {

        try {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());

            messageManager.sendCommandMessage(player, "server.success", server);

            messageManager.playPlayerSound(player, "activate");

            player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());

        }

        catch (Exception error) {

            messageManager.sendCommandMessage(player, "server.error", server);

            messageManager.playPlayerSound(player, "error");

        }
    }
}
