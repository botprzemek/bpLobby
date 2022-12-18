package pl.botprzemek.bpLobby.Command;

import io.th0rgal.oraxen.OraxenPlugin;
import io.th0rgal.oraxen.sound.CustomSound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.botprzemek.bpLobby.Lobby.Inventory.ServerSelector;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class ServerCommand implements CommandExecutor {

    private ServerSelector serverSelector;

    private CustomSound customSound;

    public ServerCommand(LobbyManager lobbyManager) {

        this.serverSelector = lobbyManager.getServerSelector();

        for (CustomSound customSound1 : OraxenPlugin.get().getSoundManager().getCustomSounds()) {

            if (customSound1.getName().equals("welcome")) this.customSound = customSound1;

        }

    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        player.openInventory(serverSelector.getInventory(player.getUniqueId()));

        customSound.play(player, player.getLocation());

        return true;

    }

}
