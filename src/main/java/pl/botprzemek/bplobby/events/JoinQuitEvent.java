package pl.botprzemek.bplobby.events;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import pl.botprzemek.bplobby.BpLobby;
import pl.botprzemek.bplobby.utils.HideShowPlayers;
import pl.botprzemek.bplobby.utils.StringSerialize;

public class JoinQuitEvent implements Listener {

    private final BpLobby instance;

    public JoinQuitEvent(BpLobby instance) {

        this.instance = instance;

    }

    private final HideShowPlayers hideShowPlayers = new HideShowPlayers();

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {

        FileConfiguration config = BpLobby.getInstanceConfig();

        String legacyString = new StringSerialize().serializeString(event.getPlayer(), config.getString("join")
                .replace("%prefix%", config.getString("prefix")));

        event.setJoinMessage(legacyString);

        hideShowPlayers.hidePlayersEveryone(event.getPlayer());

        Inventory inventory = Bukkit.createInventory(null, 54, PlaceholderAPI.setPlaceholders(event.getPlayer(), config.getString("oraxen")));

        event.getPlayer().openInventory(inventory);

    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event) {

        FileConfiguration config = BpLobby.getInstanceConfig();

        String legacyString = new StringSerialize().serializeString(event.getPlayer(), config.getString("quit")
                .replace("%prefix%", config.getString("prefix")));

        event.setQuitMessage(legacyString);

    }

}
