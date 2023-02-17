package pl.botprzemek.bpLobby.utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.lobby.config.MessageManager;

public class BungeeChannel {
    @Inject MessageManager messageManager;

    public void sendPlayer(Player player, String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());

            messageManager.sendCommandMessage(player, "server.success", server);
            messageManager.playSound(player, "activate");
            player.sendPluginMessage(instance, "BungeeCord", out.toByteArray());
        }
        catch (Exception error) {
            messageManager.sendCommandMessage(player, "server.error", server);
            messageManager.playSound(player, "error");
        }
    }
}
