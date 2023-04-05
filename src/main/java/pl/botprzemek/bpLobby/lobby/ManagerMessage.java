package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;

public class ManagerMessage {
    @Inject private BukkitAudiences audiences;
    @Inject private ConfigurationMessage configurationMessage;

    private Component replacePlaceholders(String message, String... values) {
        message = message.replace("%prefix%", this.configurationMessage.getPrefix());
        for (int i = 1; i <= values.length; i++) message = message.replace("%value_"+i+"%", values[i-1]);
        return MiniMessage.miniMessage().deserialize(message);
    }

    public void sendMessage(CommandSender sender, String message, String... values) {
        this.audiences.sender(sender).sendMessage(replacePlaceholders(message, values));
    }

    public void sendMessage(Player player, String message, String... values) {
        this.audiences.player(player).sendMessage(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public String getMessage(Player player, String message, String... values) {
        return LegacyComponentSerializer.legacySection().serialize(replacePlaceholders(PlaceholderAPI.setPlaceholders(player, message), values));
    }

    public void playSound(Player player, String soundName) {
        this.audiences.player(player).playSound(Sound.sound(Key.key(soundName), Sound.Source.NEUTRAL, 1f, 1f), Sound.Emitter.self());
    }

//    public void stopSound(Player player) {
//        this.audiences.player(player).stopSound(SoundStop.all());
//    }
//
//    public void stopSound(Player player, Sound sound) {
//        this.audiences.player(player).stopSound(sound);
//    }
}