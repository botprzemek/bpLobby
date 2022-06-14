package pl.botprzemek.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lobby implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.add("reload");
            arguments.add("menu");

            return arguments;

        }

        else if(args.length == 2) {

            List<String> playerNames = new ArrayList<>();

            for(Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }

            return playerNames;
        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage("Złe użycie komendy!");
            return false;
        }

        if (Objects.equals(args[0], "reload")) {
            bpLobby.plugin.reloadConfig();
            player.sendMessage("Pomyślnie przeładowano plik konfiguracyjny!");
        }

//        if (sender instanceof Player) {
//            if (Objects.equals(args[0], "menu")) {
//                customInventory inv = new customInventory();
//            }
//        }

        return true;
    }
}
