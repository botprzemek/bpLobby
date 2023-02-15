package pl.botprzemek.bpLobby.lobby.Config;

import eu.okaeri.injector.annotation.Inject;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.MessagesConfiguration;
import pl.botprzemek.bpLobby.lobby.LobbyManager;
import pl.botprzemek.bpLobby.lobby.Utils.Serializer;

public class MessageManager {
    @Inject private Serializer serializer;
    @Inject private MessagesConfiguration messagesConfiguration;


//    private final MessageConfig messageConfig;
//
//    private final PluginConfig pluginConfig;
//
//    private final BukkitAudiences adventure;
//
//    private final Serializer serializer;
//
//    public MessageManager(LobbyManager lobbyManager) {
//
//        messageConfig = lobbyManager.getConfigManager().getMessageConfig();
//
//        pluginConfig = lobbyManager.getConfigManager().getPluginConfig();
//
//        adventure = BukkitAudiences.create(lobbyManager.getInstance());
//
//        serializer = new Serializer();
//
//    }

    public void sendCommandMessage(Player player, String message) {
        Component serializedMessage = this.serializer.serializeString(player, message
                .replace("%prefix%", this.messagesConfiguration.getPrefix()));
        this.serializer.sendMessage(player, serializedMessage);
    }

    public void sendCommandMessage(Player player, String message, String... value) {
        message = message.replace("%prefix%", this.messagesConfiguration.getPrefix());
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

    public void playPlayerSound(Player player, String path) {

        player.playSound(player, messageConfig.getSound(path), 1F, 1F);

    }

}