package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.HashMap;

public class GuiInventory {
    public static void setupGui(BaseGui gui, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel, HashMap<Integer, GuiButton> buttons) {
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> managerMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            for (int slot : button.getSlots())
                gui.setItem(slot, ItemBuilder.from(button.getItem(managerMessage)).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationPlugin, configurationMessage, managerMessage, bungeeChannel)));
        }
    }
}
