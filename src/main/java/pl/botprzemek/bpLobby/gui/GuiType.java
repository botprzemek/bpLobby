package pl.botprzemek.bpLobby.gui;


import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.inventory.GuiPaginated;
import pl.botprzemek.bpLobby.gui.inventory.GuiStatic;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public enum GuiType {
    SERVER {
        public void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
            GuiStatic.openStatic(this.name().toLowerCase(), player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
        }
    },
    COSMETICS {
        public void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
            GuiPaginated.openPaginated(this.name().toLowerCase(), player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
        }
    };

    public abstract void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel);
}