package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.injector.annotation.Inject;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import pl.botprzemek.bpLobby.gui.GuiButton;
import pl.botprzemek.bpLobby.lobby.ManagerMessage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {

    @Comment("Spawn")
    private Location location = new Location(Bukkit.getWorld("world"), -4.5, 63.0, -1.5, 180, -5);
    @Comment("Limit")
    private int limit = 45;
    @Comment("Chat formats")
    private HashMap<String, String> formats = setupFormats();
    @Comment("Server GUI")
    private ServerGui serverGui = new ServerGui();

    @Getter
    public class ServerGui extends OkaeriConfig {
        private String title = "<gradient:#4fa943:#9ec52f><bold>Menu</bold></gradient>";
        private int size = 1;
        private HashMap<Integer, GuiButton> buttons = setupServerButtons();
    }

    private HashMap<String, String> setupFormats() {
        HashMap<String, String> formats = new HashMap<>();
        formats.put("kreator", "<color:#96d68d>");
        formats.put("admin", "<color:#d6918d>");
        formats.put("mod", "<color:#8d9fd6>");
        formats.put("artysta", "<color:#d6d58d>");
        formats.put("media", "<color:#bf8dd6>");
        formats.put("patron", "<color:#d68da8>");
        formats.put("legenda", "<color:#d6b38d>");
        formats.put("mistrz", "<color:#aad68d>");
        formats.put("bohater", "<color:#8dbed6>");
        formats.put("gracz", "<color:#9ea19d>");
        return formats;
    }

    private HashMap<Integer, GuiButton> setupServerButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        List<String> lore = new ArrayList<>();
        lore.add("Testowy przycisk");
        buttons.put(4, GuiButton.builder().slot(4).oraxenID("food_fruits_kiwi").displayName("<gradient:#4fa943:#9ec52f><bold>Survival 1.19+</bold></gradient>").lore(lore).action("survival").build());
        return buttons;
    }
}
