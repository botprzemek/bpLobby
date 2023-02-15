package pl.botprzemek.bpLobby.lobby.Utils;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Serializer {
    @Inject private Plugin plugin;

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    public Component serializeString(Player player,  String message) {
        return this.miniMessage.deserialize(PlaceholderAPI.setPlaceholders(player, message));
    }
    public void sendMessage(Player player, Component serializedMessage) {
        BukkitAudiences.create(this.plugin).player(player).sendMessage(serializedMessage);
    }
}
