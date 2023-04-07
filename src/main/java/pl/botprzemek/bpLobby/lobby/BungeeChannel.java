package pl.botprzemek.bpLobby.lobby;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import eu.okaeri.injector.annotation.Inject;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class BungeeChannel {
    @Inject private Plugin plugin;
    @Inject private ConfigurationMessage configurationMessage;
    @Inject private ManagerMessage managerMessage;

    public void sendPlayer(Player player, String server) {
        try {
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF(server.toLowerCase());
            managerMessage.sendMessage(player, configurationMessage.getCommandsServer().getSuccess(), server);
            managerMessage.playSound(player,  configurationMessage.getSounds().getActivate());
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }
        catch (Exception error) {
            managerMessage.sendMessage(player, configurationMessage.getCommandsServer().getError(), server);
            managerMessage.playSound(player, configurationMessage.getSounds().getError());
        }
    }
}
