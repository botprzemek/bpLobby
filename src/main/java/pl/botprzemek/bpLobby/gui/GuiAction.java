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

    public enum ActionType {
        SERVER {
            public void runAction(Player player, Gui gui, BungeeChannel bungeeChannel, ManagerMessage managerMessage, ConfigurationMessage configurationMessage, String actionValue) {
                bungeeChannel.sendPlayer(player, actionValue);
            }
        },
        TEXT {
            public void runAction(Player player, Gui gui, BungeeChannel bungeeChannel, ManagerMessage managerMessage, ConfigurationMessage configurationMessage, String actionValue) {
                managerMessage.sendMessage(player, configurationMessage.getEventsLink(), actionValue);
                managerMessage.playSound(player, configurationMessage.getSounds().getClick());
            }
        };

        public abstract void runAction(Player player, Gui gui, BungeeChannel bungeeChannel, ManagerMessage managerMessage, ConfigurationMessage configurationMessage, String actionValue);
    }

    public void runAction(Player player, Gui gui, BungeeChannel bungeeChannel, ManagerMessage managerMessage, ConfigurationMessage configurationMessage) {
        ActionType.valueOf(actionName.toUpperCase()).runAction(player, gui, bungeeChannel, managerMessage, configurationMessage, actionValue);
        gui.close(player);
    }
}