package pl.botprzemek.methods;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import static pl.botprzemek.bpLobby.plugin;

public class ServerConnect {

    public void connectPlayers(Player player, String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }

    public void sendMessage(Player player, String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(player.getName());
        out.writeUTF(message);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
