package pl.botprzemek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Tylko gracz może latać!");
            return true;
        }

        Player player = (Player) sender;

        if(player.getAllowFlight()){
            player.sendMessage("Wyłączyłeś latanie!");
            player.setAllowFlight(false);
        }
        else{
            player.sendMessage("Włączyłeś latanie!");
            player.setAllowFlight(true);
        }

        return true;
    }
}
