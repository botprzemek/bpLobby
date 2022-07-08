package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class Broadcast implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    String broadCast = plugin.getConfig().getString("broadcast.message");
    String usage = plugin.getConfig().getString("usage").replace("%alias%", "thank");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("broadcast.sound")).toUpperCase().replace(' ', '_');;

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.add("thank");

            return arguments;

        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0 || !(Objects.equals(args[0], "thank"))) {

            player.sendMessage(IridiumColorAPI.process(prefix + usage.replace("%command%", label)));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            return false;

        }

        else {

            player.sendMessage(IridiumColorAPI.process(prefix + broadCast));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

        }

        return true;
    }
}
