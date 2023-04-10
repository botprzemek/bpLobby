package pl.botprzemek.bpLobby.gui.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiButton;
import pl.botprzemek.bpLobby.gui.GuiInventory;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.HashMap;

public class GuiStatic {
    public static void openStatic(String name, Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
        Gui gui = Gui.gui().title(managerMessage.getComponent("<white>" + configurationPlugin.getGuis().get(name).getTitle())).rows(configurationPlugin.getGuis().get(name).getSize()).create();
        HashMap<Integer, GuiButton> buttons = configurationPlugin.getGuis().get(name).getButtons();
        GuiInventory.setupGui(name, gui, configurationPlugin, configurationMessage, managerMessage);
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            for (int slot : button.getSlots())
                gui.setItem(slot, ItemBuilder.from(button.getItem(managerMessage)).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationPlugin, configurationMessage, managerMessage, bungeeChannel)));
        }
        gui.open(player);
    }
}
