package pl.botprzemek.bpLobby.utils;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.LobbyPlugin;

public class Serializer {
    @Inject private LobbyPlugin lobbyPlugin;
    @Inject private BukkitAudiences audiences;

    public Component serializeString(Player player, String message) {
        return MiniMessage.miniMessage().deserialize(PlaceholderAPI.setPlaceholders(player, message));
    }

    public void sendMessage(Player player, Component serializedMessage) {
        this.audiences.player(player).sendMessage(serializedMessage);
    }
}
