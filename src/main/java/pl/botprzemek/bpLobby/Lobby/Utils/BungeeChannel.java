package pl.botprzemek.bpLobby.Lobby.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.BpLobby;
import pl.botprzemek.bpLobby.Lobby.Config.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class BungeeChannel {

    private BpLobby instance;

    private StringSerializer stringSerializer;

    private MessageConfig messageConfig;

    public BungeeChannel(LobbyManager lobbyManager) {

        this.instance = lobbyManager.getInstance();

        this.stringSerializer = lobbyManager.getStringSerializer();

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

    }

    public void sendPlayerToServer(Player player, String server) {

        try {

            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());

            player.sendMessage(stringSerializer.serializeServer("server.success.message", server));

            player.playSound(player, Sound.valueOf(messageConfig.getString("server.success.sound").toUpperCase()), 1, 1);

            player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());

        }

        catch (Exception error) {

            player.sendMessage(stringSerializer.serializeServer("server.error.message", server));

            player.playSound(player, Sound.valueOf(messageConfig.getString("server.error.sound").toUpperCase()), 1, 1);

        }
    }
}
