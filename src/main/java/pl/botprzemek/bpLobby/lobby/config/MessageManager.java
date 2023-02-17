package pl.botprzemek.bpLobby.lobby.config;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.MessageConfiguration;

public class MessageManager {
    @Inject private Braah braah;
    @Inject private MessageConfiguration messageConfiguration;
    @Inject private BukkitAudiences audiences;


    public void sendCommandMessage(Player player, String message) {
        Component serializedMessage = this.serializer.serializeString(player, message
                .replace("%prefix%", this.messageConfiguration.getPrefix()));
        this.serializer.sendMessage(player, serializedMessage);
    }

    public void sendCommandMessage(Player player, String message, String... value) {
        message = message.replace("%prefix%", this.messageConfiguration.getPrefix());
        for (int i = 0; i < value.length; i++) {
            message.replace("%value_"+i+"%", value[i]);
        }

        Component serializedMessage = serializer.serializeString(player, message);
        this.serializer.sendMessage(player, serializedMessage);
    }

    public String getStringMessage(Player player, String path) {

        String message = messageConfig.getMessage(path);

        Component serializedMessage = serializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);

    }

    public String getStringMessage(Player player, String path, String value) {

        String message = messageConfig.getMessage(path);

        Component serializedMessage = serializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix())
                .replace("%value%", value));

        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);

    }

    public String getConfigStringMessage(Player player, String path) {

        String message = pluginConfig.getString(path);

        Component serializedMessage = serializer.serializeString(player, message);

        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);

    }

    public void playSound(Sound sound) {
        this.audiences.all().playSound(sound);
    }

    public void playSound(Player player, Sound sound) {
        this.audiences.player(player).playSound(sound, Sound.Emitter.self());
    }

    public void stopSound(Player player) {
        this.audiences.player(player).stopSound(SoundStop.all());
    }

    public void stopSound(Player player, Sound sound) {
        this.audiences.player(player).stopSound(sound);
    }
}