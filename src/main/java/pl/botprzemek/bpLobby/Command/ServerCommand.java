package pl.botprzemek.bpLobby.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.Utils.Button;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;
import pl.botprzemek.bpLobby.Lobby.Config.MessageManager;
import pl.botprzemek.bpLobby.Lobby.Config.PluginManager;

import java.util.ArrayList;
import java.util.List;

public class ServerCommand implements CommandExecutor, TabCompleter {

    private final BungeeChannel bungeeChannel;

    private final MessageManager messageManager;

    private final PluginManager pluginManager;

    public ServerCommand(LobbyManager lobbyManager) {

        this.bungeeChannel = lobbyManager.getBungeeChannel();

        messageManager = lobbyManager.getMessageManager();

        pluginManager = lobbyManager.getPluginManager();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        switch (args.length) {
            case 0 -> {

                player.openInventory(pluginManager.getInventory(player));

                messageManager.playPlayerSound(player, "activate");

                return true;

            }
            case 1 -> {

                String serverName = args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase();

                if (!player.hasPermission("bplobby.servers." + serverName.toLowerCase())) {

                    messageManager.sendCommandMessage(player, "server.not-found", serverName);

                    messageManager.playPlayerSound(player, "error");

                    return false;

                }

                if (!pluginManager.isServerViable(serverName.toLowerCase())) {

                    messageManager.sendCommandMessage(player, "server.invalid", serverName);

                    messageManager.playPlayerSound(player, "error");

                    return false;

                }

                bungeeChannel.sendPlayerToServer(player, serverName);

                messageManager.playPlayerSound(player, "activate");

                return true;

            }
        }

        return false;

    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        List<String> servers = new ArrayList<>();

        for (Button button : pluginManager.getButtons()) {

            String serverName = button.getAction().substring(0, 1).toUpperCase() + button.getAction().substring(1).toLowerCase();

            if (sender.hasPermission("bplobby.servers." + serverName.toLowerCase())) servers.add(serverName);

        }

        return servers;

    }
}
