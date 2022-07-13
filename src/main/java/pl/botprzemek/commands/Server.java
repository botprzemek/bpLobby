package pl.botprzemek.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.methods.CreateGUI;
import pl.botprzemek.methods.ServerConnect;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static pl.botprzemek.bpLobby.plugin;

public class Server implements CommandExecutor, TabCompleter {

    String prefix = plugin.getConfig().getString("prefix");
    String sound = Objects.requireNonNull(plugin.getConfig().getString("messages.server.sound")).toUpperCase().replace(' ', '_');
    String connect = plugin.getConfig().getString("messages.server.success");
    String usage = plugin.getConfig().getString("usage").replace("%alias%", "serwer");

    int guiSize = plugin.getConfig().getInt("servers.gui.size");
    String guiName = plugin.getConfig().getString("servers.gui.name");
    String guiFillingMaterial = plugin.getConfig().getString("servers.gui.filling").toUpperCase().replace(' ', '_');

    ServerConnect serverConnect = new ServerConnect();
    CreateGUI createGUI = new CreateGUI();

    List<String> serverNames = plugin.getConfig().getStringList("servers.name");

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 1) {

            return serverNames;

        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Inventory gui = createGUI.createGUI(null, guiSize, IridiumColorAPI.process(guiName), guiFillingMaterial);

        Player player = (Player) sender;

        if (args.length == 0) {
            player.openInventory(gui);
            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);

        }

        if (args.length == 1) {

            String server = plugin.getConfig().getString("servers.server." + args[0]);

            if (!(serverNames.contains(args[0]))) {

                player.sendMessage(IridiumColorAPI.process(prefix + usage.replace("%command%", label)));
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
                return false;

            }

            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            serverConnect.connectPlayers(player, server);
            serverConnect.sendMessage(player, IridiumColorAPI.process(prefix + connect.replace("%server%", args[0])));

        }

//        if (args.length == 2) {
//
//            String server = plugin.getConfig().getString("servers.server." + args[0]);
//
//            if (Bukkit.getPlayer(args[1]) == null) {
//
//                player.sendMessage(IridiumColorAPI.process(prefix + usage.replace("%command%", label)));
//                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
//                return false;
//
//            }
//
//            Player target = Bukkit.getPlayer(args[1]);
//            if (target != player) player.sendMessage(IridiumColorAPI.process(prefix + other.replace("%server%", args[0]).replace("%player%", target.getName())));
//            target.sendMessage(IridiumColorAPI.process(prefix + connect.replace("%server%", args[0])));
//            serverConnect.connectPlayers(target, server);
//            player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
//
//        }

        return true;
    }
}