package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.TextComponent;

import java.util.HashMap;

public class GuiServer {
    public Gui createGui(TextComponent title, int size, HashMap<Integer, GuiItem> items) {
        Gui gui = Gui.gui().title(title).rows(size).create();
        items.forEach(gui::setItem);
        return gui;
    }
}