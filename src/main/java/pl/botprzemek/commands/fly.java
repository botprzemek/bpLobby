package pl.botprzemek.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Fly implements CommandExecutor, TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {

            List<String> playerNames = new ArrayList<>();

            for(Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }

            return playerNames;
        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Tylko gracz może latać!");
            return true;
        }

        Player player = (Player) sender;

        if(args.length == 0){
            if(player.getAllowFlight()){
                player.sendMessage("Wyłączyłeś latanie dla gracza" + player.getName() + "!");
                player.setAllowFlight(false);
            }
            else{
                player.sendMessage("Włączyłeś latanie dla gracza " + player.getName() + "!");
                player.setAllowFlight(true);
            }
        }

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);

            if(player.getAllowFlight()){
                player.sendMessage("Wyłączyłeś latanie dla gracza " + target.getName() + "!");
                target.setAllowFlight(false);
            }
            else{
                player.sendMessage("Włączyłeś latanie dla gracza " + target.getName() + "!");
                target.setAllowFlight(true);
            }
        }

        return true;
    }
}
