package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class GuiServer {
    public Gui createGui(Component title, int size, HashMap<Integer, GuiItem> items) {
        Gui gui = Gui.gui().title(title).rows(size).create();
        items.forEach(gui::setItem);
        return gui;
    }

    public HashMap<Integer, GuiItem> setupItems(HashMap<Integer, ItemStack> items) {
        return null;
    }
}