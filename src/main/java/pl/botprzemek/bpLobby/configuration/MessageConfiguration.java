package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class MessageConfiguration extends OkaeriConfig {
    @Comment("Prefix")
    private String prefix = "<gradient:#FFE04A:#FFA400>★ <bold>ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>";
    @Comment("Reload Command")
    private CommandsReload commandsReload = new CommandsReload();

    @Comment("Server Command")
    private CommandsServer commandsServer = new CommandsServer();
    @Comment("Vanish Command")
    private CommandsVanish commandsVanish = new CommandsVanish();
    private EventsConnect eventsConnect = new EventsConnect();
    private EventsDisconnect eventsDisonnect = new EventsDisconnect();
    private EventsChat eventsChat = new EventsChat();
    private Sounds sounds = new Sounds();

    @Getter
    private class CommandsReload extends OkaeriConfig {
        private String reloadSuccess = "%prefix% Konfiguracja <gradient:#FFE04A:#FFA400><bold>przeładowana!</bold></gradient>";
        private String reloadFailed = "%prefix% Wystąpił <gradient:#FFE04A:#FFA400><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#FFE04A:#FFA400><bold>konfiguracji!</bold></gradient>";
    }

    @Getter
    private class CommandsServer extends OkaeriConfig {
        private String serverSuccess = "%prefix% Zostałeś/aś przeniesiony/a na <gradient:#FFE04A:#FFA400><bold><bold>%value%!</bold></gradient>";
        private String serverError = "%prefix% Wystąpił <gradient:#FFE04A:#FFA400><bold>błąd</bold></gradient> przy przenoszeniu na <gradient:#FFE04A:#FFA400><bold>%value%!</bold></gradient>";
        private String serverInvalid = "%prefix% Serwer, który wpisałeś/aś nie <gradient:#FFE04A:#FFA400><bold>istnieje!</bold></gradient>";
        private String serveNotFound = "%prefix% Serwer, który wpisałeś/aś nie jest <gradient:#FFE04A:#FFA400><bold>dostępny!</bold></gradient>";
    }

    @Getter
    private class CommandsVanish extends OkaeriConfig {
        private String vanishShow = "%prefix% Teraz widzisz innych <gradient:#FFE04A:#FFA400><bold>graczy!</bold></gradient>";
        private String vanishHide = "%prefix% Ukryłeś/aś innych <gradient:#FFE04A:#FFA400><bold>graczy!</bold></gradient>";
    }

    @Getter
    private class EventsConnect extends OkaeriConfig {
        private String connectJoin = "%prefix% %player_name% dołączył/a do <gradient:#FFE04A:#FFA400><bold>Lobby! (%value% gracz/y)</bold></gradient>";
        private String connectQuit = "%prefix% %player_name% opuścił/a <gradient:#FFE04A:#FFA400><bold>Lobby! (%value% gracz/y)</bold></gradient>";
    }

    @Getter
    private class EventsDisconnect extends OkaeriConfig {
        private String disconnectTimeout = "<gradient:#FFE04A:#FFA400>★ <bold>ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>\n\nMinął czas na <gradient:#FFE04A:#FFA400><bold>wybór trybu!</bold></gradient>\nPołącz się <gradient:#FFE04A:#FFA400><bold>ponownie!</bold></gradient>\n\n<gradient:#FFE04A:#FFA400>★ <bold>ᴅᴄ.ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>";
    }

    @Getter
    private class EventsChat extends OkaeriConfig {
        private String chatSuccess = "<color:#FFE04A>★<reset>%luckperms_prefix%%player_name% <color:#FFA400>★<reset> %value%";
        private String chatFailed = "%prefix% Nie masz <gradient:#FFE04A:#FFA400><bold>dostępu</bold></gradient> do wysyłania <gradient:#FFE04A:#FFA400><bold>wiadomości!</bold></gradient>";
    }

    @Getter
    private class Sounds extends OkaeriConfig {
        private String soundsSuccess = "ui_toast_challenge_complete";
        private String soundsActivate = "block_note_block_pling";
        private String soundsStep = "block_note_block_xylophone";
        private String soundsError = "block_note_block_bit";
    }
}
