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
import pl.botprzemek.methods.Teleporting;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class Teleport implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    String notPlayer = plugin.getConfig().getString("messages.teleport.not-player");
    String notCorrect = plugin.getConfig().getString("messages.teleport.not-correct");
    String toSelf = plugin.getConfig().getString("messages.teleport.toself");
    String selfTeleport = plugin.getConfig().getString("messages.teleport.self");
    String playerTeleport = plugin.getConfig().getString("messages.teleport.player");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.teleport.sound")).toUpperCase().replace(' ', '_');

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            Bukkit.getLogger().info(notPlayer);
            return null;

        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {

            Bukkit.getLogger().info(notPlayer);
            return false;

        }

        Teleporting teleporting = new Teleporting();

        if (args.length == 0) {

            player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
            return false;

        }

        else if (args.length == 1) {

            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {

                player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
                return false;

            }

            if (target.equals(player)) {

                player.sendMessage(IridiumColorAPI.process(prefix + toSelf));
                return false;

            }

            else {

                player.sendMessage(IridiumColorAPI.process(prefix + selfTeleport.replace("%player%", args[0])));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                teleporting.selfTeleport(player, target);

            }

        }

        else if (args.length == 2) {

            Player target = Bukkit.getPlayer(args[0]);
            Player target2 = Bukkit.getPlayer(args[1]);

            if (target == null || target2 == null) {

                player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
                return false;

            }

            else {

                if (!(player.equals(target))) player.sendMessage(IridiumColorAPI.process(prefix + playerTeleport.replace("%player%", args[0]).replace("%target%", args[1])));
                target.sendMessage(IridiumColorAPI.process(prefix + selfTeleport.replace("%player%", args[1])));
                teleporting.playerTeleport(target, target2);

            }

        }

        return true;
    }
}

