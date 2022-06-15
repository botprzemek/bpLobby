package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby;

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

        String prefix = bpLobby.plugin.getConfig().getString("prefix");
        String notPlayer = bpLobby.plugin.getConfig().getString("messages.fly.not-player");

        if(!(sender instanceof Player)){
            sender.sendMessage(IridiumColorAPI.process(prefix + notPlayer));
            return true;
        }

        String playerOn = bpLobby.plugin.getConfig().getString("messages.fly.player.on");
        String playerOff = bpLobby.plugin.getConfig().getString("messages.fly.player.off");
        String selfOn = bpLobby.plugin.getConfig().getString("messages.fly.self.on");
        String selfOff = bpLobby.plugin.getConfig().getString("messages.fly.self.off");

        Player player = (Player) sender;

        if(args.length == 0){
            if(player.getAllowFlight()){
                player.sendMessage(IridiumColorAPI.process(prefix + selfOff));
                player.setAllowFlight(false);
            }
            else{
                player.sendMessage(IridiumColorAPI.process(prefix + selfOn));
                player.setAllowFlight(true);
            }
        }

        if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);

            if(player.getAllowFlight()){
                assert playerOff != null;
                assert target != null;
                player.sendMessage(IridiumColorAPI.process(prefix + playerOff.replace("%player%", target.getName())));
                player.setAllowFlight(false);
            }
            else{
                assert playerOn != null;
                assert target != null;
                player.sendMessage(IridiumColorAPI.process(prefix + playerOn.replace("%player%", target.getName())));
                player.setAllowFlight(true);
            }
        }

        return true;
    }
}
