package pl.botprzemek.bpLobby.Lobby.Config;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;
import pl.botprzemek.bpLobby.Lobby.Utils.Serializer;

public class MessageManager {

    private final MessageConfig messageConfig;

    private final PluginConfig pluginConfig;

    private final BukkitAudiences adventure;

    private final Serializer serializer;

    public MessageManager(LobbyManager lobbyManager) {

        messageConfig = lobbyManager.getConfigManager().getMessageConfig();

        pluginConfig = lobbyManager.getConfigManager().getPluginConfig();

        adventure = BukkitAudiences.create(lobbyManager.getInstance());

        serializer = new Serializer();

    }

    public void sendCommandMessage(Player player, String path) {

        String message = messageConfig.getCommandMessage(path);

        Component serializedMessage = serializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        adventure.player(player).sendMessage(serializedMessage);

    }

    public void sendCommandMessage(Player player, String path, String value) {

        String message = messageConfig.getCommandMessage(path);

        Component serializedMessage = serializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix())
                .replace("%value%", value));

        adventure.player(player).sendMessage(serializedMessage);

    }

    public void sendEventMessage(Player player, String path) {

        String message = messageConfig.getEventMessage(path);

        Component serializedMessage = serializer.serializeString(player, message
                .replace("%prefix%", messageConfig.getPrefix()));

        adventure.player(player).sendMessage(serializedMessage);

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