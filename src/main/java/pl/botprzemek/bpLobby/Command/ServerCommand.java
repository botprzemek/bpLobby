package pl.botprzemek.bpLobby.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class ServerCommand implements CommandExecutor {

    private ServerSelector serverSelector;

    public ServerCommand(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        Inventory inventory = serverSelector.getInventory(player.getUniqueId());

        player.openInventory(inventory);

        return true;

    }

}
