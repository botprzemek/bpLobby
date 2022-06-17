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
import pl.botprzemek.methods.WalkingSpeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Speed implements CommandExecutor, TabCompleter {

    String prefix = bpLobby.plugin.getConfig().getString("prefix");
    String notCorrect = bpLobby.plugin.getConfig().getString("messages.speed.not-correct");
    String useSpeed = bpLobby.plugin.getConfig().getString("messages.speed.self.use");
    String selfSpeed = bpLobby.plugin.getConfig().getString("messages.speed.self.set");
    String playerSpeed = bpLobby.plugin.getConfig().getString("messages.speed.player.set");
    String sound = Objects.requireNonNull(bpLobby.plugin.getConfig().getString("messages.speed.sound")).toUpperCase().replace(' ', '_');

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.add("1");
            arguments.add("2");
            arguments.add("3");
            arguments.add("4");

            return arguments;

        } else if (args.length == 2) {

            List<String> playerNames = new ArrayList<>();

            for (Player player : Bukkit.getOnlinePlayers()) {
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
            player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
        }

        if (args.length == 1) {

            if (args[0].length() == 1) {
                int speed = Integer.parseInt(args[0]);
                float walkingSpeed = new WalkingSpeed().setWalkSpeed(player, speed);
                player.sendMessage(IridiumColorAPI.process(prefix + useSpeed.replace("%speed%", args[0])));
                player.setWalkSpeed(walkingSpeed);
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            }

        }

        if (args.length == 2) {

            Player target = Bukkit.getPlayer(args[1]);

            if (args[0].length() == 1 && target != null) {

                    int speed = Integer.parseInt(args[0]);
                    float walkingSpeed = new WalkingSpeed().setWalkSpeed(target, speed);

                    target.setWalkSpeed(walkingSpeed);
                    if(!player.equals(target)) player.sendMessage(IridiumColorAPI.process(prefix + playerSpeed.replace("%player%", target.getName()).replace("%speed%", args[0])));
                    target.sendMessage(IridiumColorAPI.process(prefix + selfSpeed.replace("%player%", target.getName()).replace("%speed%", args[0])));
                    target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

            }

            else {
                player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
            }

        }

        return true;
    }
}
