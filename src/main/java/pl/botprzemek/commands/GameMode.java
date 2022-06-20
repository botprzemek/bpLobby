package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.botprzemek.inventories.GameModeGui;
import pl.botprzemek.methods.WalkingSpeed;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class GameMode implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    String notPlayer = plugin.getConfig().getString("messages.speed.not-player");
    String notCorrect = plugin.getConfig().getString("messages.speed.not-correct");

    List<String> gameModes = plugin.getConfig().getStringList("game-mode.names");

    String selfSpeed = plugin.getConfig().getString("messages.speed.self.set");
    String playerSpeed = plugin.getConfig().getString("messages.speed.player.set");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.speed.sound")).toUpperCase().replace(' ', '_');

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {

            Bukkit.getLogger().info(notPlayer);
            return null;

        }

        if (args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.addAll(gameModes);

            return arguments;

        }

        else if (args.length == 2) {

            List<String> playerNames = new ArrayList<>();

            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }

            return playerNames;
        }

        return null;
    }

    GameModeGui gameModeGui = new GameModeGui();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {

            Bukkit.getLogger().info(notPlayer);
            return false;

        }

        if (args.length == 0) {

            gameModeGui.openGui(player);
            return false;

        }

        if (args.length == 1) {

            if (Integer.parseInt(args[0]) > 4) {

                player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
                return false;

            }

        }

        if (args.length == 2) {

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                player.sendMessage(IridiumColorAPI.process(prefix + notCorrect.replace("%command%", label)));
                return false;
            }

            if (args[0].length() == 1) {

                int speed = Integer.parseInt(args[0]);
                float walkingSpeed = new WalkingSpeed().setWalkSpeed(target, speed);

                target.setWalkSpeed(walkingSpeed);
                if(!player.equals(target)) player.sendMessage(IridiumColorAPI.process(prefix + selfSpeed.replace("%player%", target.getName()).replace("%speed%", args[0])));
                target.sendMessage(IridiumColorAPI.process(prefix + playerSpeed.replace("%player%", player.getName()).replace("%speed%", args[0])));
                target.playSound(target.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

            }

        }

        return true;
    }
}
