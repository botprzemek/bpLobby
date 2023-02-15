package pl.botprzemek.bpLobby.lobby.Utils;

import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class Serializer {

    private final MiniMessage mm;

    public Serializer() {

        mm = MiniMessage.miniMessage();

    }

    public Component serializeString(Player player,  String message) {

        return mm.deserialize(PlaceholderAPI.setPlaceholders(player, message));

    }

}
