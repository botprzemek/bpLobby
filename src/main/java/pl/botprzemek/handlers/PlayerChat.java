package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby;

import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class PlayerChat implements Listener {

    public PlayerChat(bpLobby plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    String messagePrefix = IridiumColorAPI.process(Objects.requireNonNull(plugin.getConfig().getString("prefix")));
    String messageMessage = IridiumColorAPI.process(Objects.requireNonNull(plugin.getConfig().getString("messages.chat.message")));
    String format = IridiumColorAPI.process(Objects.requireNonNull(plugin.getConfig().getString("messages.chat.format")));

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();

        if (!(player.hasPermission("bplobby.chat"))) {

            player.sendMessage(messagePrefix + messageMessage);
            event.setCancelled(true);
            return;

        }

        else {

            event.setFormat(format);

        }
    }
}
