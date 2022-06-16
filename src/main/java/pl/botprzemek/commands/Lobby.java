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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Lobby implements CommandExecutor, TabCompleter {

    String prefix = bpLobby.plugin.getConfig().getString("prefix");
    List<String> lobby = bpLobby.plugin.getConfig().getStringList("commands-aliases.lobby");
    String[] lobbyReload = lobby.get(0).split("\\|");
    String configFalse = bpLobby.plugin.getConfig().getString("messages.config.false");
    String configReload = bpLobby.plugin.getConfig().getString("messages.config.reload");
    String sound = Objects.requireNonNull(bpLobby.plugin.getConfig().getString("messages.config.sound")).toUpperCase().replace(' ', '_');

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1) {

            List<String> arguments = new ArrayList<>();

            arguments.add(lobbyReload[0]);
            arguments.add("menu");

            return arguments;

        }

        else if(args.length == 2) {

            List<String> playerNames = new ArrayList<>();

            for(Player player : Bukkit.getOnlinePlayers()) {
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
            player.sendMessage(IridiumColorAPI.process(prefix + configFalse + "\n" + lobbyReload[1]));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            return false;
        }

        if (Objects.equals(args[0], lobbyReload[0])) {
            bpLobby.plugin.reloadConfig();
            player.sendMessage(IridiumColorAPI.process(prefix + configReload));
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
        }

        return true;
    }
}
