package pl.botprzemek.bpLobby.gui.inventories;

import dev.triumphteam.gui.guis.Gui;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiInventory;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class GuiStatic {
    public static void open(String name, Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
        Gui gui = Gui.gui().title(managerMessage.getComponent("<white>" + configurationPlugin.getGuis().get(name).getTitle())).rows(configurationPlugin.getGuis().get(name).getSize()).create();
        GuiInventory.setupGui(gui, configurationPlugin, configurationMessage, managerMessage, bungeeChannel, configurationPlugin.getGuis().get(name).getButtons());
        gui.open(player);
    }
}
