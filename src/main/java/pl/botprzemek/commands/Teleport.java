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
import pl.botprzemek.methods.FlyingSpeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class Teleport implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    String notPlayer = plugin.getConfig().getString("messages.fly.not-player");
    String notCorrect = plugin.getConfig().getString("messages.fly.not-correct");
    String playerOn = plugin.getConfig().getString("messages.fly.player.on");
    String playerOff = plugin.getConfig().getString("messages.fly.player.off");
    String playerSpeed = plugin.getConfig().getString("messages.fly.player.speed");
    String selfOn = plugin.getConfig().getString("messages.fly.self.on");
    String selfOff = plugin.getConfig().getString("messages.fly.self.off");
    String selfSpeed = plugin.getConfig().getString("messages.fly.self.speed");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.fly.sound")).toUpperCase().replace(' ', '_');

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info(notPlayer);
            return null;
        }

        if (args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.add("1");
            arguments.add("2");
            arguments.add("3");
            arguments.add("4");
            arguments.add("on");
            arguments.add("off");

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

        if (!(sender instanceof Player)) {
            Bukkit.getLogger().info(notPlayer);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));

        if (args.length == 1) {

            if (args[0].length() == 1) {

                int speed = Integer.parseInt(args[0]);
                float flyingSpeed = new FlyingSpeed().setFlySpeed(player, speed);
                player.sendMessage(IridiumColorAPI.process(prefix + selfSpeed.replace("%speed%", args[0])));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setFlySpeed(flyingSpeed);

            }

            else if (args[0].equals("on")) {

                player.sendMessage(IridiumColorAPI.process(prefix + selfOn));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(true);

            }

            else if (args[0].equals("off")) {

                player.sendMessage(IridiumColorAPI.process(prefix + selfOff));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(false);

            }

        }

        if (args.length == 2) {

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));

            if (args[0].length() == 1 && target != null) {

                int speed = Integer.parseInt(args[0]);
                float flyingSpeed = new FlyingSpeed().setFlySpeed(target, speed);

                target.setWalkSpeed(flyingSpeed);
                if(!player.equals(target)) player.sendMessage(IridiumColorAPI.process(prefix + playerSpeed.replace("%player%", target.getName()).replace("%speed%", args[0])));
                target.sendMessage(IridiumColorAPI.process(prefix + selfSpeed.replace("%player%", player.getName()).replace("%speed%", args[0])));
                target.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

            }

            else if(args[0].equals("on") && target != null) {

                if(!player.equals(target)) player.sendMessage(IridiumColorAPI.process(prefix + playerOn.replace("%player%", target.getName()).replace("%speed%", args[0])));
                target.sendMessage(IridiumColorAPI.process(prefix + selfOn.replace("%player%", player.getName()).replace("%speed%", args[0])));
                target.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(true);

            }

            else if(args[0].equals("off") && target != null) {

                if(!player.equals(target)) player.sendMessage(IridiumColorAPI.process(prefix + playerOff.replace("%player%", target.getName()).replace("%speed%", args[0])));
                target.sendMessage(IridiumColorAPI.process(prefix + selfOff.replace("%player%", player.getName()).replace("%speed%", args[0])));
                target.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                player.setAllowFlight(false);

            }

        }

        return true;
    }
}

