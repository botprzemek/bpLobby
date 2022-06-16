package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fly implements CommandExecutor, TabCompleter {

    String prefix = bpLobby.plugin.getConfig().getString("prefix");
    String notPlayer = bpLobby.plugin.getConfig().getString("messages.fly.not-player");
    String playerOn = bpLobby.plugin.getConfig().getString("messages.fly.player.on");
    String playerOff = bpLobby.plugin.getConfig().getString("messages.fly.player.off");
    String selfOn = bpLobby.plugin.getConfig().getString("messages.fly.self.on");
    String selfOff = bpLobby.plugin.getConfig().getString("messages.fly.self.off");
    String selfSpeed = bpLobby.plugin.getConfig().getString("messages.fly.self.speed");
    String sound = Objects.requireNonNull(bpLobby.plugin.getConfig().getString("messages.fly.sound")).toUpperCase().replace(' ', '_');

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

        Player player = (Player) sender;

        if(!(sender instanceof Player)){
            sender.sendMessage(IridiumColorAPI.process(prefix + notPlayer));
            return true;
        }

        if(args.length == 0){
            if(player.getAllowFlight()){
                player.sendMessage(IridiumColorAPI.process(prefix + selfOff));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(false);
            }

            else{
                player.sendMessage(IridiumColorAPI.process(prefix + selfOn));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(true);
            }
        }

        if(args.length == 1){

            if(args[0].length() == 1){
                if(Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 6){
                    float speed = (float) (0.1 * Integer.parseInt(args[0]));
                    String speedString = String.valueOf(10 * speed);
                    player.sendMessage(IridiumColorAPI.process(prefix + selfSpeed.replace("%speed%", speedString)));
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                    player.setFlySpeed(speed);
                }
            }

            else {

                Player target = Bukkit.getPlayer(args[0]);

                assert target != null;
                if(target.getAllowFlight()){
                    assert playerOff != null;
                    player.sendMessage(IridiumColorAPI.process(prefix + playerOff.replace("%player%", target.getName())));
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                    player.setAllowFlight(false);
                }

                else{
                    assert playerOn != null;
                    player.sendMessage(IridiumColorAPI.process(prefix + playerOn.replace("%player%", target.getName())));
                    player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                    player.setAllowFlight(true);
                }
            }
        }

        return true;
    }
}
