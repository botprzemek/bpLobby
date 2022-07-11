package pl.botprzemek.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static pl.botprzemek.bpLobby.plugin;

public class PlayerSee {

    public void showPlayers(Player player) {

        for(Player show : Bukkit.getOnlinePlayers()) {

            player.showPlayer(plugin, show);

        }

    }

    public void hidePlayers(Player player) {

        for(Player hide : Bukkit.getOnlinePlayers()) {

            player.hidePlayer(plugin, hide);

        }

    }

    public void hidePlayersEveryone(Player player) {

        for(Player hide : Bukkit.getOnlinePlayers()) {

            player.hidePlayer(plugin, hide);
            hide.hidePlayer(plugin, player);

        }

    }

}
