package pl.botprzemek.methods;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import static pl.botprzemek.bpLobby.plugin;

public class SwitchGameMode {

    String prefix = plugin.getConfig().getString("prefix");
    String gameModeAlreadySet = plugin.getConfig().getString("messages.game-mode.already-set");
    String gameModeSet = plugin.getConfig().getString("messages.game-mode.set");

    public void switchingGameMode(Player player, GameMode gameMode) {

        if (player.getGameMode().equals(gameMode)) {

            player.closeInventory();
            player.sendMessage(IridiumColorAPI.process(prefix + gameModeAlreadySet));
            return;

        }

        else {

            player.closeInventory();
            player.sendMessage(IridiumColorAPI.process(prefix + gameModeSet));
            player.setGameMode(gameMode);

        }
    }
}
