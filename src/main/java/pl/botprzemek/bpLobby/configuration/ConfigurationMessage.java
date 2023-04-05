package pl.botprzemek.bpLobby.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;

@Getter
public class ConfigurationMessage extends OkaeriConfig {
    @Comment("Prefix")
    private String prefix = "<gradient:#FFE04A:#FFA400>★ <bold>exotia.net</bold> ★</gradient>";
    @Comment("No permission message")
    private CommandsNoPermission commandsNoPermission = new CommandsNoPermission();
    @Comment("Invalid usage message")
    private CommandsInvalid commandsInvalid = new CommandsInvalid();
    @Comment("Player Command")
    private CommandsPlayer commandsPlayer = new CommandsPlayer();
    @Comment("Reload Command")
    private CommandsReload commandsReload = new CommandsReload();
    @Comment("Server Command")
    private CommandsServer commandsServer = new CommandsServer();
    @Comment("Vanish Command")
    private CommandsVanish commandsVanish = new CommandsVanish();
    @Comment("Connect Events")
    private EventsConnect eventsConnect = new EventsConnect();
    @Comment("Disconnect Events")
    private EventsDisconnect eventsDisonnect = new EventsDisconnect();
    @Comment("Disconnect Events")
    private EventsChat eventsChat = new EventsChat();
    @Comment("Sounds")
    private Sounds sounds = new Sounds();

    @Getter
    public class CommandsNoPermission extends OkaeriConfig {
        private String failed = "%prefix% Nie masz dostępu do tej <gradient:#FFE04A:#FFA400><bold>komendy!</bold></gradient>";
    }

    @Getter
    public class CommandsInvalid extends OkaeriConfig {
        private String invalid = "%prefix% Niepoprawne użycie <gradient:#FFE04A:#FFA400><bold>komendy!</bold></gradient>";
        private String invalidUsage = "%prefix% Niepoprawne użycie <gradient:#FFE04A:#FFA400><bold>komendy!</bold></gradient> Spróbuj użyć <gradient:#FFE04A:#FFA400><bold>%value_1%!</bold></gradient>";
    }

    @Getter
    public class CommandsPlayer extends OkaeriConfig {
        private String offline = "%prefix% Wybrany gracz jest <gradient:#FFE04A:#FFA400><bold>offline!</bold></gradient>";
        private String only = "%prefix% Użycie dostępne tylko dla <gradient:#FFE04A:#FFA400><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class CommandsReload extends OkaeriConfig {
        private String success = "%prefix% Konfiguracja <gradient:#FFE04A:#FFA400><bold>przeładowana!</bold></gradient>";
        private String failed = "%prefix% Wystąpił <gradient:#FFE04A:#FFA400><bold>błąd</bold></gradient> przy przeładowywaniu <gradient:#FFE04A:#FFA400><bold>konfiguracji!</bold></gradient>";
    }

    @Getter
    public class CommandsServer extends OkaeriConfig {
        private String success = "%prefix% Zostałeś/aś przeniesiony/a na <gradient:#FFE04A:#FFA400><bold><bold>%value_1%!</bold></gradient>";
        private String error = "%prefix% Wystąpił <gradient:#FFE04A:#FFA400><bold>błąd</bold></gradient> przy przenoszeniu na <gradient:#FFE04A:#FFA400><bold>%value_1%!</bold></gradient>";
        private String invalid = "%prefix% Serwer, który wpisałeś/aś nie <gradient:#FFE04A:#FFA400><bold>istnieje!</bold></gradient>";
        private String notFound = "%prefix% Serwer, który wpisałeś/aś nie jest <gradient:#FFE04A:#FFA400><bold>dostępny!</bold></gradient>";
    }

    @Getter
    public class CommandsVanish extends OkaeriConfig {
        private String show = "%prefix% Teraz widzisz innych <gradient:#FFE04A:#FFA400><bold>graczy!</bold></gradient>";
        private String hide = "%prefix% Ukryłeś/aś innych <gradient:#FFE04A:#FFA400><bold>graczy!</bold></gradient>";
    }

    @Getter
    public class EventsConnect extends OkaeriConfig {
        private String join = "%prefix% %value_1% dołączył/a do <gradient:#FFE04A:#FFA400><bold>Lobby!</bold></gradient>";
        private String quit = "%prefix% %value_1% opuścił/a <gradient:#FFE04A:#FFA400><bold>Lobby!</bold></gradient>";
    }

    @Getter
    public class EventsDisconnect extends OkaeriConfig {
        private String timeout = "<gradient:#FFE04A:#FFA400>★ <bold>ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>\n\nMinął czas na <gradient:#FFE04A:#FFA400><bold>wybór trybu!</bold></gradient>\nPołącz się <gradient:#FFE04A:#FFA400><bold>ponownie!</bold></gradient>\n\n<gradient:#FFE04A:#FFA400>★ <bold>ᴅᴄ.ʙʜɪᴠᴇ.ᴘʟ</bold> ★</gradient>";
    }

    @Getter
    public class EventsChat extends OkaeriConfig {
        private String success = "<color:#FFE04A>★<reset>%luckperms_prefix% %player_name% <color:#FFA400>★<reset> %value%";
        private String failed = "%prefix% Nie masz <gradient:#FFE04A:#FFA400><bold>dostępu</bold></gradient> do wysyłania <gradient:#FFE04A:#FFA400><bold>wiadomości!</bold></gradient>";
    }

    @Getter
    public class Sounds extends OkaeriConfig {
        private String success = "ui_toast_challenge_complete";
        private String activate = "block_note_block_pling";
        private String step = "block_note_block_xylophone";
        private String error = "block_note_block_bit";
    }
}
