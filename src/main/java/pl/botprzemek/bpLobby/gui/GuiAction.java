package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.guis.Gui;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Builder
@Getter
public class GuiAction extends OkaeriConfig {
    private String actionName;
    private String actionValue;

    public void runAction(Player player, Gui gui, BungeeChannel bungeeChannel, ManagerMessage managerMessage, ConfigurationMessage configurationMessage) {
        switch (actionName) {
            case "server" -> {
                bungeeChannel.sendPlayer(player, actionValue);
            }
            case "text" -> {
                managerMessage.sendMessage(player, configurationMessage.getEventsLink(), actionValue);
                managerMessage.playSound(player, configurationMessage.getSounds().getClick());
            }
        }
        gui.close(player);
    }
}
