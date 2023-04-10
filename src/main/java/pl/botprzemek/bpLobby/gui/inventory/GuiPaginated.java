package pl.botprzemek.bpLobby.gui.inventory;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.PaginatedGui;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.gui.GuiButton;
import pl.botprzemek.bpLobby.gui.GuiInventory;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.HashMap;

public class GuiPaginated {
    public static void openPaginated(String name, Player player, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage, BungeeChannel bungeeChannel) {
        PaginatedGui gui = Gui.paginated().title(managerMessage.getComponent("<white>" + configurationPlugin.getGuis().get(name).getTitle())).rows(configurationPlugin.getGuis().get(name).getSize()).create();
        HashMap<Integer, GuiButton> buttons = configurationPlugin.getGuis().get(name).getButtons();
        GuiButton next = configurationPlugin.getGuis().get(name).getButtonNext();
        GuiButton previous = configurationPlugin.getGuis().get(name).getButtonPrevious();
        GuiInventory.setupGui(name, gui, configurationPlugin, configurationMessage, managerMessage);
        gui.setPageSize(configurationPlugin.getGuis().get(name).getItemsPerPage());
        gui.setItem(next.getSlots().get(0), ItemBuilder.from(next.getItem(managerMessage)).asGuiItem(event -> {
            gui.next();
            managerMessage.playSound(player, configurationMessage.getSounds().getClick());
        }));
        gui.setItem(previous.getSlots().get(0), ItemBuilder.from(previous.getItem(managerMessage)).asGuiItem(event -> {
            gui.previous();
            managerMessage.playSound(player, configurationMessage.getSounds().getClick());
        }));
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            gui.addItem(ItemBuilder.from(button.getItem(managerMessage)).asGuiItem(event -> button.getAction().runAction((Player) event.getWhoClicked(), configurationPlugin, configurationMessage, managerMessage, bungeeChannel)));
        }
        gui.open(player);
    }
}
