package pl.botprzemek.bpLobby.Lobby.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.Lobby.Config.InventoryConfig;
import pl.botprzemek.bpLobby.Lobby.Config.MessageConfig;
import pl.botprzemek.bpLobby.Lobby.LobbyManager;

public class StringSerializer {

    private MiniMessage mm = MiniMessage.miniMessage();

    private MessageConfig messageConfig;

    private InventoryConfig inventoryConfig;

    public StringSerializer(LobbyManager lobbyManager) {

        this.messageConfig = lobbyManager.getConfigManager().getMessageConfig();

        this.inventoryConfig = lobbyManager.getConfigManager().getInventoryConfig();

    }

    public String serializeText(String string) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(string
                .replace("%prefix%", messageConfig.getMessage("prefix"))));

    }

    public String serializeTextFromPath(String path, String playerName, String message) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(messageConfig.getMessage(path)
                .replace("%player_name%", playerName)
                .replace("%prefix%", messageConfig.getMessage("prefix"))
                .replace("%message%", message)));

    }

    public String serializeInventoryTitle(String string) {

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(inventoryConfig.getInventoryTitle(string)));

    }

    public String serializeJoinQuit(Player player, String string) {

        String stringPlaceholders = PlaceholderAPI
                .setPlaceholders(player, messageConfig.getMessage(string)
                .replace("%prefix%", messageConfig.getMessage("prefix")));

        return LegacyComponentSerializer.legacySection()
                .serialize(mm.deserialize(stringPlaceholders));

    }

}
