package pl.botprzemek.bpLobby.Command;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.Config.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.BungeeChannel;
import pl.botprzemek.bpLobby.Lobby.Utils.StringSerializer;

import java.util.List;

public class ServerCommand implements CommandExecutor, TabCompleter {

    private BungeeChannel bungeeChannel;

    private ServerSelector serverSelector;

    private MessageConfig messageConfig;

    private StringSerializer stringSerializer;

    public ServerCommand(LobbyManager lobbyManager) {

        this.bungeeChannel = lobbyManager.getBungeeChannel();

        this.serverSelector = lobbyManager.getServerSelector();

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

        this.stringSerializer = lobbyManager.getStringSerializer();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return false;

        switch (args.length) {
            case 0 -> {

                player.openInventory(serverSelector.getInventory(player.getUniqueId()));

                player.playSound(player, Sound.valueOf(messageConfig.getString("server.success.sound").toUpperCase()), 1, 1);

                return true;

            }
            case 1 -> {

                if (!serverSelector.getServerSelectorNames().contains(args[0].substring(0, 1).toUpperCase() + args[0].substring(1).toLowerCase())) {

                    player.sendMessage(stringSerializer.serializeServer("server.not-found.message", args[0]));

                    player.playSound(player, Sound.valueOf(messageConfig.getString("server.not-found.sound").toUpperCase()), 1, 1);

                    return false;

                }

                bungeeChannel.sendPlayerToServer(player, args[0].toLowerCase());

                return true;

            }

            default -> {

                player.sendMessage(stringSerializer.serializePlainTextWithPapi(player, messageConfig.getString("server.usage.message")));

                player.playSound(player, Sound.valueOf(messageConfig.getString("server.usage.sound").toUpperCase()), 1, 1);

                return false;
            }
        }
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return null;

        return serverSelector.getServerSelectorNames();

    }
}
