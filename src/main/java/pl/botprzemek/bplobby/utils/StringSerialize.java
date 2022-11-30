package pl.botprzemek.bplobby.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import pl.botprzemek.bplobby.BpLobby;

public class StringSerialize {

    private final MiniMessage mm = BpLobby.getMiniMessage();

    public String serializeString(Player player, String string) {

        String stringPlaceholders = PlaceholderAPI.setPlaceholders(player, string);

        Component prefixComponent = mm.deserialize(stringPlaceholders);

        return  LegacyComponentSerializer.legacySection().serialize(prefixComponent);

    }

}
