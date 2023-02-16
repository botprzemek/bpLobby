package pl.botprzemek.bpLobby.lobby.util;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Serializer {
    @Inject private Plugin plugin;
    @Inject private BukkitAudiences audiences;

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public Component serializeString(Player player,  String message) {
        return this.miniMessage.deserialize(PlaceholderAPI.setPlaceholders(player, message));
    }

    public void sendMessage(Player player, Component serializedMessage) {
        this.audiences.player(player).sendMessage(serializedMessage);
    }
}
