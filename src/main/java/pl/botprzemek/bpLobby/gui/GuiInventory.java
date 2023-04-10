package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.BaseGui;
import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationMessage;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

public class GuiInventory {
    public static void setupGui(String name, BaseGui gui, ConfigurationPlugin configurationPlugin, ConfigurationMessage configurationMessage, ManagerMessage managerMessage) {
        GuiItem filling = ItemBuilder.from(Material.AIR).asGuiItem();
        gui.setItem(configurationPlugin.getGuis().get(name).getSlotsEmpty(), filling);
        gui.disableAllInteractions();
        gui.setOpenGuiAction(event -> managerMessage.playSound((Player) event.getPlayer(), configurationMessage.getSounds().getClick()));
    }
}
