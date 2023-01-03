package pl.botprzemek.bpLobby.Lobby.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.Lobby.Config.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class StringSerializer {

    private MiniMessage mm = MiniMessage.miniMessage();

    private MessageConfig messageConfig;

    public StringSerializer(LobbyManager lobbyManager) {

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

    }

    public String serializeText(String string) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(string
                .replace("%prefix%", messageConfig.getMessage("prefix"))));

    }

    public String serializeTextFromPath(String path, Player player, String message) {

        String originalMessage = messageConfig.getMessage(path)
                .replace("%message%", message);

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(PlaceholderAPI.setPlaceholders(player, originalMessage.replace("%prefix%", messageConfig.getMessage("prefix")))));

    }

    public String serializePlainTextWithPapi(Player player, String string) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(PlaceholderAPI.
                        setPlaceholders(player, string)
                        .replace("%prefix%", messageConfig.getMessage("prefix"))));

    }

    public String serializeJoinQuit(Player player, String string) {

        String stringPlaceholders = PlaceholderAPI
                .setPlaceholders(player, messageConfig.getMessage(string)
                .replace("%prefix%", messageConfig.getMessage("prefix")));

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(stringPlaceholders));

    }

    public String serializeServer(String string, String server) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(messageConfig.getMessage(string)
                        .replace("%prefix%", messageConfig.getMessage("prefix"))
                        .replace("%server%", server)));

    }

}
