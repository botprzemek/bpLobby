package pl.botprzemek.bpLobby.gui;


import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.inventories.GuiPaginated;
import pl.botprzemek.bpLobby.gui.inventories.GuiStatic;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public enum GuiType {
    SERVER {
        public void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
            GuiStatic.open(this.name().toLowerCase(), player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
        }
    },
    COSMETICS {
        public void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
            GuiPaginated.open(this.name().toLowerCase(), player, configurationPlugin, configurationMessage, managerMessage, bungeeChannel);
        }
    };

    public abstract void open(Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel);
}