package pl.botprzemek.bpLobby.Lobby.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.BpLobby;

import java.util.HashMap;
import java.util.UUID;

public class HideShowPlayers {

    private BpLobby instance;

    private HashMap<UUID, Boolean> hiddenPlayers = new HashMap<>();

    public HideShowPlayers(BpLobby instance) {

        this.instance = instance;

    }

    public void showPlayers(Player player) {

        for(Player target : Bukkit.getOnlinePlayers()) {

            player.showPlayer(instance, target);

            hiddenPlayers.put(player.getUniqueId(), false);

        }

    }

    public void hidePlayers(Player player) {

        if (hiddenPlayers.size() == 1) {

            for(UUID targetUUID : hiddenPlayers.keySet()) Bukkit.getPlayer(targetUUID).hidePlayer(instance, player);

        }

        for(Player target : Bukkit.getOnlinePlayers()) {

            player.hidePlayer(instance, target);

            hiddenPlayers.put(player.getUniqueId(), true);

        }

    }

    public HashMap<UUID, Boolean> getHiddenPlayers() {

        return hiddenPlayers;

    }

}