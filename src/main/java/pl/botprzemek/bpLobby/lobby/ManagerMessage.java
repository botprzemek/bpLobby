package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;

import java.util.ArrayList;
import java.util.List;

public class ManagerMessage {
    @Inject private BukkitAudiences audiences;
    @Inject private ConfigurationMessage configurationMessage;

    private Component replacePlaceholders(String message, String... values) {
        message = message.replace("%prefix%", this.configurationMessage.getPrefix());
        for (int i = 1; i <= values.length; i++) message = message.replace("%value_"+i+"%", values[i-1]);
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public void sendMessage(CommandSender sender, String message, String... values) {
        this.audiences.sender(sender).sendMessage(replacePlaceholders(message, values));
    }

    public void sendMessage(Player player, String message, String... values) {
        this.audiences.player(player).sendMessage(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public String getMessage(String message) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + message));
    }

    public List<String> getMessages(List<String> messages) {
        List<String> serializedMessages = new ArrayList<>();
        for (String message : messages) serializedMessages.add(LegacyComponentSerializer.legacySection().serialize(replacePlaceholders("<white>" + message)));
        return serializedMessages;
    }

    public Component getComponent(String message) {
        return MiniMessage.miniMessage().deserialize(message.replace("&", "").replace("§f", ""));
    }

    public String getComponent(Player player, String message, String... values) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public void playSound(Player player, String soundName) {
        player.playSound(player, Sound.valueOf(soundName.toUpperCase()), 1F, 1F);
    }
}