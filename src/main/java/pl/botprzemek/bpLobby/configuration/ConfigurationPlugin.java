package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import pl.botprzemek.bpLobby.gui.GuiAction;
import pl.botprzemek.bpLobby.gui.GuiButton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Getter
public class ConfigurationPlugin extends OkaeriConfig {

    @Comment("Spawn")
    private final Location location = new Location(Bukkit.getWorld("world"), -4.5, 63.0, -1.5, 180, -5);
    @Comment("Limit")
    private final int limit = 45;
    @Comment("Chat formats")
    private final HashMap<String, String> formats = setupFormats();
    @Comment("Hotbar buttons")
    private final HashMap<Integer, GuiButton> hotbarButtons = setupHotbarButtons();
    @Comment("GUIs")
    private final HashMap<String, GuiConfig> guis = setupGuis();

    private HashMap<String, GuiConfig> setupGuis() {
        HashMap<String, GuiConfig> guis = new HashMap<>();
        guis.put("server", new GuiServer());
        guis.put("cosmetics", new GuiCosmetics());
        return guis;
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
        formats.put("default", "<color:#9ea19d>");
        return formats;
    }

    private HashMap<Integer, GuiButton> setupHotbarButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(4, GuiButton.builder()
                .slots(List.of(4))
                .oraxenID("ksiazka_wybor")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Wybierz Serwer</bold></gradient>")
                .lore(Arrays.asList("<gray>Kliknij, aby otworzyć", "<gray>menu wyboru serwerów!"))
                .action(GuiAction.builder().name("open").value("server").build())
                .build());
        buttons.put(8, GuiButton.builder()
                .slots(List.of(8))
                .oraxenID("ksiazka_wybor")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Wybierz Czapkę</bold></gradient>")
                .lore(Arrays.asList("<gray>Kliknij, aby otworzyć", "<gray>menu wyboru czapek!"))
                .action(GuiAction.builder().name("open").value("cosmetics").build())
                .build());
        return buttons;
    }

    private HashMap<Integer, GuiButton> setupServerButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(0, GuiButton.builder()
                .slots(Arrays.asList(0, 1, 2, 3, 9, 10, 11, 12))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Odkryj nasz serwer", "<gray>i jego możliwości."))
                .action(GuiAction.builder().name("server").value("survival").build()).build());
        buttons.put(1, GuiButton.builder()
                .slots(Arrays.asList(5, 6, 7, 8, 14, 15, 16, 17))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Tymczasowo", "<gray>Puste."))
                .action(GuiAction.builder().name("server").value("minigames").build()).build());
        buttons.put(2, GuiButton.builder()
                .slots(Arrays.asList(18, 19, 20, 21, 22, 23, 24, 25, 26))
                .oraxenID("required_invisible_item")
                .displayName("Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>")
                .lore(Arrays.asList("<gray>Przejdź do linku", "<gray>na czacie!"))
                .action(GuiAction.builder().name("text").value("<hover:show_text:'Kliknij, aby <gradient:#4fa943:#9ec52f><bold>dołączyć!</bold></gradient>'><click:OPEN_URL:'https://dc.exotia.net/'>Link do naszego <gradient:#4fa943:#9ec52f><bold>discorda!</bold></gradient></click></hover>").build()).build());
        return buttons;
    }

    private HashMap<Integer, GuiButton> setupCosmeticsButtons() {
        HashMap<Integer, GuiButton> buttons = new HashMap<>();
        buttons.put(0, GuiButton.builder()
                .slots(List.of(0))
                .oraxenID("czapka_helm_gornika")
                .displayName("Mycka Grubiorza")
                .lore(Arrays.asList("鵭 <gradient:#4fa943:#9ec52f><bold>Odblokowane!</bold></gradient>", "<gray>Pozwala na bezpieczne", "<gray>przemierzanie mrocznych", "<gray>korytarzy i jaskiń."))
                .action(GuiAction.builder().name("set_item").value("czapka_helm_gornika").build()).build());
        return buttons;
    }

    @Getter
    public static class GuiConfig extends OkaeriConfig {
        private String title;
        private int size;
        private GuiButton buttonNext;
        private GuiButton buttonPrevious;
        private HashMap<Integer, GuiButton> buttons;
    }

    @Getter
    public class GuiServer extends GuiConfig {
        private final String title = "✟ꟹ";
        private final int size = 3;
        private final HashMap<Integer, GuiButton> buttons = setupServerButtons();
    }

    @Getter
    public class GuiCosmetics extends GuiConfig {
        private final String title = "✟ꟺ";
        private final int size = 3;
        private final GuiButton buttonNext = GuiButton.builder()
                .slots(List.of(26))
                .oraxenID("ksiazka_wybor")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Dalej</bold></gradient>")
                .lore(Arrays.asList("<gray>Kliknij, aby otworzyć", "<gray>następną stronę!"))
                .action(GuiAction.builder().name("place").value("holder").build())
                .build();
        private final GuiButton buttonPrevious = GuiButton.builder()
                .slots(List.of(19))
                .oraxenID("ksiazka_wybor")
                .displayName("<gradient:#4fa943:#9ec52f><bold>Powróć</bold></gradient>")
                .lore(Arrays.asList("<gray>Kliknij, aby otworzyć", "<gray>poprzednią stronę!"))
                .action(GuiAction.builder().name("place").value("holder").build())
                .build();
        private final HashMap<Integer, GuiButton> buttons = setupCosmeticsButtons();
    }
}
