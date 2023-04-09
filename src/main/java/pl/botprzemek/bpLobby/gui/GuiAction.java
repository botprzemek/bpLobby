package pl.botprzemek.bpLobby.gui;

import eu.okaeri.configs.OkaeriConfig;
import io.th0rgal.oraxen.api.OraxenItems;
import lombok.Builder;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

@Builder
@Getter
public class GuiAction extends OkaeriConfig {
    private String name;
    private String value;

    public void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
        ActionType.valueOf(name.toUpperCase()).runAction(player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel, value);
    }

    public enum ActionType {
        SERVER {
            public void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, String value) {
                bungeeChannel.sendPlayer(player, value);
                player.closeInventory();
            }
        },
        TEXT {
            public void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, String value) {
                managerMessage.sendMessage(player, configurationMessage.getEventsLink(), value);
                managerMessage.playSound(player, configurationMessage.getSounds().getClick());
                player.closeInventory();
            }
        },
        OPEN {
            public void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, String value) {
                GuiType.valueOf(value.toUpperCase()).open(player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
            }
        },
        SET_ITEM {
            public void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, String value) {
                ItemStack item = OraxenItems.getItemById(value).build();
                if (item != null) player.getInventory().setItem(EquipmentSlot.HEAD, item);
                managerMessage.playSound(player, configurationMessage.getSounds().getClick());
            }
        };

        public abstract void runAction(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, String value);
    }
}