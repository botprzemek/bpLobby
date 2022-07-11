package pl.botprzemek.handlers;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.botprzemek.bpLobby;

import java.util.Objects;

import static pl.botprzemek.bpLobby.*;

public class PlayerChat implements Listener {

    public PlayerChat(bpLobby plugin) { Bukkit.getPluginManager().registerEvents(this, plugin); }

    String messagePrefix = Objects.requireNonNull(plugin.getConfig().getString("prefix"));
    String messageMessage = Objects.requireNonNull(plugin.getConfig().getString("messages.chat.message"));
    String format = Objects.requireNonNull(plugin.getConfig().getString("messages.chat.format"));

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        Player player = event.getPlayer();
        String message = event.getMessage();
        String group = perms.getPrimaryGroup(player);
        World world = event.getPlayer().getWorld();
        String playerPrefix = chat.getGroupPrefix(world, group).replace("#", "GRADIENT:");

        if (!(player.hasPermission("bplobby.chat"))) {

            player.sendMessage(IridiumColorAPI.process(messagePrefix + messageMessage));
            event.setCancelled(true);

        }

        else {

            event.setFormat(IridiumColorAPI.process(format.replace("%prefix%", playerPrefix).replace("%player%", player.getName()).replace("%message%", message)));

        }
    }
}
