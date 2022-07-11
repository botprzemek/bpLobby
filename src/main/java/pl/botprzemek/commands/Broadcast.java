package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import static pl.botprzemek.bpLobby.plugin;

public class Broadcast implements CommandExecutor, TabCompleter {

    String broadCast = plugin.getConfig().getString("prefix") + plugin.getConfig().getString("broadcast.message");

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

        if (args.length == 1) {

            Bukkit.broadcastMessage(IridiumColorAPI.process(broadCast));

        }

        return true;
    }
}
