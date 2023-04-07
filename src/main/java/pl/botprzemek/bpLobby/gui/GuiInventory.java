package pl.botprzemek.bpLobby.gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import eu.okaeri.injector.annotation.Inject;
import eu.okaeri.injector.annotation.PostConstruct;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import pl.botprzemek.bpLobby.configuration.ConfigurationPlugin;
import pl.botprzemek.bpLobby.lobby.BungeeChannel;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.HashMap;

public class GuiInventory {
    @Inject private BungeeChannel bungeeChannel;
    @Inject private ConfigurationPlugin configurationPlugin;
    @Inject private ManagerMessage managerMessage;

    @Getter
    private Gui gui;

    @PostConstruct
    public void onConstruct() {
         this.gui = createGui(
                managerMessage.getComponent(configurationPlugin.getServerGui().getTitle()),
                configurationPlugin.getServerGui().getSize(),
                 configurationPlugin.getServerGui().getButtons());
    }

    public Gui createGui(Component title, Integer size, HashMap<Integer, GuiButton> buttons) {
        Gui gui = Gui.gui().title(title).rows(size).create();
        gui.disableAllInteractions();
        for (int key : buttons.keySet()) {
            GuiButton button = buttons.get(key);
            gui.setItem(key, ItemBuilder.from(button.getItem(managerMessage)).asGuiItem(event -> bungeeChannel.sendPlayer((Player) event.getWhoClicked(), button.getAction())));
        }
        return gui;
    }
}