package pl.botprzemek.bpLobby.Lobby.Utils;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.Lobby.Config.Configs.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class MessageManager {

    private final MessageConfig messageConfig;

    private final BukkitAudiences adventure;

    private final StringSerializer stringSerializer;

    public MessageManager(LobbyManager lobbyManager) {

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

        adventure = BukkitAudiences.create(lobbyManager.getInstance());

        stringSerializer = new StringSerializer();

    }

    public void sendCommandMessage(Player player, String path) {

        String message = messageConfig.getCommandMessage(path);

        Component serializedMessage = stringSerializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        adventure.player(player).sendMessage(serializedMessage);

    }

    public void sendCommandMessage(Player player, String path, String value) {

        String message = messageConfig.getCommandMessage(path);

        Component serializedMessage = stringSerializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix())
                .replace("%value%", value));

        adventure.player(player).sendMessage(serializedMessage);

    }

    public void sendEventMessage(Player player, String path) {

        String message = messageConfig.getEventMessage(path);

        Component serializedMessage = stringSerializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        adventure.player(player).sendMessage(serializedMessage);

    }

    public String getMessageString(Player player, String path) {

        String message = messageConfig.getMessage(path);

        Component serializedMessage = stringSerializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);

    }

    public String getMessageString(Player player, String path, String value) {

        String message = messageConfig.getMessage(path);

        Component serializedMessage = stringSerializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix())
                .replace("%value%", value));

        return LegacyComponentSerializer.legacySection().serialize(serializedMessage);

    }

    public void playPlayerSound(Player player, String path) {

        player.playSound(player, messageConfig.getSound(path), 1F, 1F);

    }

}