package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.injector.Injector;
import eu.okaeri.injector.annotation.Inject;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import pl.botprzemek.bpLobby.gui.GuiAction;
import pl.botprzemek.bpLobby.gui.GuiButton;
import pl.botprzemek.bpLobby.gui.GuiInventory;
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
        private GuiButton selector = GuiButton.builder()
                .slots(Arrays.asList(4))
                .oraxenID("book_selector")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Wybierz Serwer</bold></gradient>")
                .lore(Arrays.asList("<gray>Kliknij, aby otworzyć", "<gray>wybór serwerów!"))
                .build();
        private String title = "✟ꟹ";
        private int size = 3;
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
        buttons.put(0, GuiButton.builder()
                .slots(Arrays.asList(0,1,2,3,9,10,11,12))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Odkryj nasz serwer", "<gray>i jego możliwości"))
                .action(GuiAction.builder().actionName("server").actionValue("survival").build()).build());
        buttons.put(1, GuiButton.builder()
                .slots(Arrays.asList(5,6,7,8,14,15,16,17))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Tymczasowo", "<gray>Puste"))
                    .action(GuiAction.builder().actionName("server").actionValue("minigames").build()).build());
        buttons.put(2, GuiButton.builder()
                .slots(Arrays.asList(18,19,20,21,22,23,24,25,26))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Przejdź do linku", "<gray>na czacie!"))
                .action(GuiAction.builder().actionName("text").actionValue("<hover:show_text:'Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>'><click:OPEN_URL:'https://dc.exotia.net/'>Link do naszego <gradient:#4fa943:#9ec52f><bold>discorda!</bold></gradient></click></hover>").build()).build());
        return buttons;
    }
}
