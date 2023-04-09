package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.UUID;

public class HiddenPlayers {
    private final HashMap<UUID, Boolean> hiddenPlayers = new HashMap<>();
    @Inject
    private Plugin plugin;

    public Boolean getPlayer(Player player) {
        return hiddenPlayers.get(player.getUniqueId());
    }

    public void hidePlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.hidePlayer(plugin, target);
        hiddenPlayers.put(player.getUniqueId(), true);
    }

    public void showPlayers(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) player.showPlayer(plugin, target);
        hiddenPlayers.remove(player.getUniqueId());
    }

    public void removePlayer(Player player) {
        if (hiddenPlayers.get(player.getUniqueId()) != null) hiddenPlayers.remove(player.getUniqueId());
    }
}
