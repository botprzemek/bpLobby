package pl.botprzemek.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import pl.botprzemek.methods.PlayerSee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class HideShowPlayers implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    List<String> aliases = plugin.getConfig().getStringList("commands-aliases.player-see");
    String playerHide = plugin.getConfig().getString("messages.hide-show.hide");
    String playerShow = plugin.getConfig().getString("messages.hide-show.show");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.hide-show.sound")).toUpperCase().replace(' ', '_');

    String usage = plugin.getConfig().getString("usage").replace("%alias%", aliases.get(0));

    PlayerSee playerSee = new PlayerSee();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {

            List<String> playerNames = new ArrayList<>();

            for(Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }

            return playerNames;
        }

        else if(args.length == 2) {

            for (String alias:aliases) //for each construct
            {

                System.out.println(alias);

            }

            return aliases;

        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (args.length == 0 || args.length == 1) {

            player.sendMessage(IridiumColorAPI.process(prefix + usage.replace("%command%", label)));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            return false;

        }


        if (Objects.equals(args[1], aliases.get(0))) {

            playerSee.hidePlayers(player);
            player.sendMessage(IridiumColorAPI.process(prefix + playerHide));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

        }

        if (Objects.equals(args[1], aliases.get(1))) {

            playerSee.showPlayers(player);
            player.sendMessage(IridiumColorAPI.process(prefix + playerShow));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

        }

        return true;
    }
}
