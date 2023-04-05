package pl.botprzemek.bpLobby.lobby;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.sound.SoundStop;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.MessageConfiguration;
import pl.botprzemek.bpLobby.configuration.PluginConfiguration;
import pl.botprzemek.bpLobby.utils.Serializer;

public class ManagerMessage {
    @Inject private PluginConfiguration pluginConfiguration;
    @Inject private MessageConfiguration messageConfiguration;
    @Inject private BukkitAudiences audiences;
    @Inject private Serializer serializer;


    public void sendCommandMessage(Player player, String message) {
        Component serializedMessage = this.serializer.serializeString(player, message
                .replace("%prefix%", this.messageConfiguration.getPrefix()));
        this.serializer.sendMessage(player, serializedMessage);
    }

    public void sendCommandMessage(Player player, String message, String... value) {
        message = message.replace("%prefix%", this.messageConfiguration.getPrefix());
        for (int i = 0; i < value.length; i++) message.replace("%value_"+i+"%", value[i]);
        Component serializedMessage = serializer.serializeString(player, message);
        this.serializer.sendMessage(player, serializedMessage);
    }

//    public String getStringMessage(Player player, String path) {
//        String message = messageConfiguration.getMessage(path);
//        Component serializedMessage = serializer.serializeString(player, message
//                .replace("%prefix%", messageConfiguration.getPrefix()));
//        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);
//    }

//    public String getStringMessage(Player player, String path, String value) {
//        String message = messageConfiguration.getMessage(path);
//        Component serializedMessage = serializer.serializeString(player, message
//                .replace("%prefix%", messageConfiguration.getPrefix())
//                .replace("%value%", value));
//        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);
//    }

    public void playSound(Sound sound) {
        this.audiences.all().playSound(sound);
    }

    public void playSound(Player player, Sound sound) {
        this.audiences.player(player).playSound(sound, Sound.Emitter.self());
    }

    public void playSound(Player player, String soundName) {
        this.audiences.player(player).playSound(Sound.sound(Key.key(soundName), Sound.Source.NEUTRAL, 1f, 1f), Sound.Emitter.self());
    }

    public void stopSound(Player player) {
        this.audiences.player(player).stopSound(SoundStop.all());
    }

    public void stopSound(Player player, Sound sound) {
        this.audiences.player(player).stopSound(sound);
    }
}