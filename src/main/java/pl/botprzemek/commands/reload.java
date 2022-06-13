package pl.botprzemek.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String [] args) {

        Player player = (Player) sender;

        if (args.length == 0){
            player.sendMessage("Złe użycie komendy!");
            return false;
        }

        if(Objects.equals(args[0], "reload")){
            player.sendMessage("Pomyślnie przeładowano plik konfiguracyjny!");
        }

//        bpLobby config = new bpLobby();
//        config.reloadConfig();
        return true;
    }
}
