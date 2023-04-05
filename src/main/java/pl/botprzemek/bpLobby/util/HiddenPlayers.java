package pl.botprzemek.bpLobby.util;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class HiddenPlayers {
    @Inject private Plugin plugin;

    private HashMap<UUID, Boolean> hiddenPlayers;

    public HashMap<UUID, Boolean> getPlayers() {
        return hiddenPlayers;
    }
    public Boolean getPlayer(Player player) {
        return hiddenPlayers.get(player.getUniqueId());
    }

    public void hidePlayer(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.hidePlayer(plugin, target);
        hiddenPlayers.put(player.getUniqueId(), true);
    }

    public void showPlayer(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.showPlayer(plugin, target);
        hiddenPlayers.remove(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        hiddenPlayers.remove(player.getUniqueId());
    }
}
